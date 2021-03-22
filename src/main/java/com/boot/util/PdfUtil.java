package com.boot.util;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname PdfUtil
 * @Description TODO
 * @Date 2020/7/13 4:20 下午
 * @Created by hly
 */
public class PdfUtil {

    public static void main(String[] args) throws Exception {

        Map<String, String> data = new HashMap<>();
        data.put("dealerName", "国际测试测试测试测试");
        export("/Users/sunxiaohu/Downloads/template.pdf", "/Users/sunxiaohu/Downloads/", data);
        System.out.println("生成完成");
    }

    /**
     * @param path 生成地址
     * @param data 数据 pdf表单 key-value
     * @description: pdf 根据模版生成
     * @Param: templatePath pdf模板所在路径
     * @return: void
     * @author: hly
     * @time: 2020/7/13 1:47 下午
     */
    public static String export(String templatePath, String path, Map<String, String> data) throws Exception {
        PdfReader reader = new PdfReader(templatePath);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfStamper ps = new PdfStamper(reader, bos);

        // 使用中文字体
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
        fontList.add(bf);


        AcroFields fields = ps.getAcroFields();
        fields.setSubstitutionFonts(fontList);
        // 参数
        fillData(fields, data);

        //必须要调用这个，否则文档不会生成的
        ps.setFormFlattening(true);
        ps.close();

        //生成pdf路径存放的路径
        String pdfPath = path + "合同_" + System.currentTimeMillis() + ".pdf";
        OutputStream fos = new FileOutputStream(pdfPath);
        fos.write(bos.toByteArray());
        fos.flush();
        fos.close();
        bos.close();
        return pdfPath;
    }

    /**
     * 填充模板中的数据
     */
    public static void fillData(AcroFields fields, Map<String, String> data) {
        try {
            for (String key : data.keySet()) {
                String value = data.get(key);
                // 为字段赋值,注意字段名称是区分大小写的
                fields.setField(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
