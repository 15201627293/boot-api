package com.boot.excel;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Classname Servlets
 * @Description Servlet工具类
 * @Date 2020/9/14 11:19 上午
 */
public class Servlets {
    /**
     * 获取在不同浏览器下的下载文件的转码规则
     *
     * @param request  request
     * @param fileName 原名，不进行转码前名称
     * @return String 示例 "filename=\"" + downloadName + "\"";
     * @date 2018年10月30日 22:35:05
     */
    public static String getDownName(HttpServletRequest request, String fileName) {
        String result = "";
        String httpUserAgent = request.getHeader("User-Agent");
        try {
            if (!StringUtils.isEmpty(httpUserAgent)) {
                String downloadName = URLEncoder.encode(fileName, "UTF-8");
                httpUserAgent = httpUserAgent.toLowerCase();
                if (httpUserAgent.contains("msie")) {
                    result = "attachment;filename=\"" + downloadName + "\"";
                } // Opera浏览器只能采用filename*
                else if (httpUserAgent.contains("opera")) {
                    result = "attachment;filename*=UTF-8''" + downloadName;
                } else if (httpUserAgent.contains("trident") || httpUserAgent.contains("edge")) {
                    result = "attachment;filename=\"" + URLEncoder.encode(fileName, "UTF-8");
                }
                // Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
                else if (httpUserAgent.contains("applewebkit")) {
                    result = "attachment;filename=\"" + downloadName + "\"";
                }
                // FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
                else if (httpUserAgent.contains("mozilla")) {
                    result = "attachment;filename*=UTF-8''" + downloadName;
                } else {
                    result = "attachment;filename=\"" + downloadName + "\"";
                }
            }
        } catch (UnsupportedEncodingException e) {
            result = "attachment;filename=\"" + fileName + "\"";
        }
        return result;
    }
}
