package com.boot.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Classname HttpResponseUtil
 * @Description TODO
 * @Date 2020/6/13 6:56 下午
 * @Created by hly
 */
public class HttpResponseUtil {

    public static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";
    public static final String CONTENT_TYPE_TEXT = "text/plain; charset=utf-8";
    private static Logger log = LoggerFactory.getLogger(HttpResponseUtil.class);

    public static void returnJson(HttpServletResponse res, String jsonStr) {
        try {
            writeResponse(jsonStr, CONTENT_TYPE_JSON, res);
        } catch (IOException e) {
            log.error("", e);
        }
    }

    public static void returnJson(HttpServletResponse res, Object obj) {
        String jsonStr = JSON.toJSONString(obj);
        try {
            writeResponse(jsonStr, CONTENT_TYPE_JSON, res);
        } catch (IOException e) {
            log.error("", e);
        }
    }

    public static void writeResponse(String message, String type, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType(type);
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.write(message);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    public static void asyncResponse(HttpServletRequest req, HttpServletResponse res, String data) {
        asyncResponse(req, res, data, CONTENT_TYPE_TEXT);
    }

    public static void asyncResponse(HttpServletRequest req, HttpServletResponse res, String data, String contentType) {
        res.setHeader("Pragma", "No-cache");
        res.setHeader("Cache-Control", "no-cache");
        res.setContentType(contentType);
        PrintWriter pw = null;
        try {
            pw = res.getWriter();
            pw.write(data);
            //开启异步时，无需flush，异步完成，通知servlet返回响应
            req.getAsyncContext().complete();
        } catch (IOException e) {
            log.error("", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}
