package com.boot.util;

import com.boot.entity.User;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.token.BasicOAuthToken;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname TokenUtil
 * @Description TODO
 * @Date 2020/9/1 3:41 下午
 */
public class TokenUtil {

    /**
     * 获取bearer token
     */
    public static String getToken(HttpServletRequest ruquest) {
        String authorization = ruquest.getHeader("Authorization");
        if (StringUtil.nullity(authorization)) {
            return "";
        }
        if (!authorization.startsWith("Bearer")) {
            return "";
        }
        return authorization.replace("Bearer", "").trim();
    }

    /**
     * 获取 accessToken
     * @param user
     * @return
     */
    public static Map<String, Object> accessToken(User user){
        BasicOAuthToken token=getAccessToken();
//        addAccessToken(token.getAccessToken(), user.getId()+"");
        Map<String, Object> map=new HashMap<String,Object>();
        map.put("accessToken", token.getAccessToken());
        map.put("expiresIn", token.getExpiresIn());
        map.put("user", user);
        return map;
    }

    /**
     * 生成accessToken
     * @return
     */
    public static BasicOAuthToken getAccessToken() {
        OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
        BasicOAuthToken token = null;
        try {
            token = new BasicOAuthToken(oauthIssuerImpl.accessToken(), Long.parseLong("1800"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        }
        return token;
    }

//    public static void addAccessToken(String accessToken, String user_id) {
//        if(null!=redisUtil.get("user_id_"+user_id)){
//            String token=redisUtil.get("user_id_"+user_id).toString();
//            redisUtil.del("user_token_"+token);
//        }
//        redisUtil.set("user_token_"+accessToken, user_id, 1800);
//        redisUtil.set("user_id_"+user_id, accessToken, 1800);
//    }

}
