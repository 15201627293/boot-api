package com.boot.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.boot.constant.FromEnums;
import com.boot.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname LogInterceptor
 * @Description TODO
 * @Date 2020/6/13 1:51 下午
 * @Created by hly
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Value("${hly.sercet}")
    private String hlySercet;

    @Value("${get}")
    private String get;
    @Value("${post}")
    private String post;
    @Value("${put}")
    private String put;
    @Value("${delete}")
    private String delete;

    @Autowired

    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("请求路径：{}", request.getRequestURI());
        // 从header中获取密钥
        String sercet = request.getHeader("sercet");
        if (StringUtils.isBlank(sercet)) {
            throw new RuntimeException("请在请求头中添加您平台的密钥，详情联系Dream！");
        }
        String method = request.getMethod();
        String from = "";
        if (get.equals(method)) {
            from = request.getParameter("from");
            if (StringUtils.isBlank(from)) {
                throw new RuntimeException("请加上所属平台 from 1-test 2-dev 3-prod");
            }
        } else if (post.equals(method) || put.equals(method) || delete.equals(method)) {

            String json = new RequestWrapper(request).getBodyString();
            JSONObject jsonObject = JSON.parseObject(json);
            from = jsonObject.getString("from");
            if (StringUtils.isBlank(from)) {
                throw new RuntimeException("请加上所属平台 from 1-test 2-dev 3-prod");
            }
        }
        boolean flagFrom = FromEnums.TEST.getCode().equals(from) || FromEnums.TEST.getCode().equals(from) || FromEnums.TEST.getCode().equals(from) ? true : false;
        boolean flagSercet = hlySercet.equals(sercet) ? true : false;
        if (!flagFrom || !flagSercet) {
            throw new RuntimeException("非法调用！请联系Dream！");
        }
        // 查看token
        String token = TokenUtil.getToken(request);
        if(StringUtils.isBlank(token)){
            throw new RuntimeException("没有凭证或凭证不正确");
        }
        return true;
    }
}
