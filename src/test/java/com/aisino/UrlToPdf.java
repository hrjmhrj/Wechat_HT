package com.aisino;


import com.aisino.entity.system.Json;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UrlToPdf {

    @Value("${file.defaultPath}")
    private String fileRootPath;

    @Value("${server.port}")
    private String serverport;

    @Value("${jianyang.filepath}")
    private String jianyangfilepath;

    @Value("${jianyang.port}")
    private String jianyangport;


        public Json PDFdownload(@RequestBody Map<String, String> hm) {

            String fileUrl=hm.get("url");
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            String date = df.format(new Date());
            System.out.println("data获取的值");
            System.out.println(date);
            String fileLocal=fileRootPath  + "pdf/" +date+"/";
            System.out.println("fileLocal的值");
            System.out.println(fileLocal);
            try {
                URL httpurl = new URL(fileUrl);
                String fileName = getFileNameFromUrl(fileUrl);
                System.out.println(fileName);
                File f = new File(fileLocal + fileName);
                FileUtils.copyURLToFile(httpurl, f);

                return new Json(true, "下载保存成功","localhost:80"+fileLocal + fileName);
            } catch (Exception e) {
                e.printStackTrace();
                return new Json(false, "下载保存失败","" );
            }
        }


    public static String getFileNameFromUrl(String url){
        String name = new Long(System.currentTimeMillis()).toString() + ".pdf";
        int index = url.lastIndexOf("/");
        if(index > 0){
            name = url.substring(index + 1);
            if(name.trim().length()>0){
                return name;
            }
        }
        return name;
    }

    @Test
    public void urltopdf(){
        System.out.println("jianyangfilepath===");
        System.out.println(jianyangfilepath);
        System.out.println(jianyangport);
        String fileUrl="https://inv.jss.com.cn/group5/M01/01/15/wKj6zl8eTtaICcFtAACBiIaWfvkAAZU0QDEKl0AAIGg455.pdf";
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String date = df.format(new Date());
        System.out.println("data获取的值");
        System.out.println(date);
        String fileLocal=jianyangfilepath  +date+"/";
        System.out.println("fileLocal的值");
        System.out.println(fileLocal);
        try {
            URL httpurl = new URL(fileUrl);
            String fileName = getFileNameFromUrl(fileUrl);
            System.out.println(fileName);
            File f = new File(fileLocal + fileName);
            FileUtils.copyURLToFile(httpurl, f);
            System.out.println("保存的路径");

            System.out.println(jianyangport+fileLocal + fileName);
            //return new Json(true, "下载保存成功","localhost:80"+fileLocal + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            //return new Json(false, "下载保存失败","" );
        }

    }

    }




