package com.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.test.entity.system.Json;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

    @Autowired
    private RestTemplate restTemplate;

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

    //查系统类型ping指定ip
    @Test
    public void contextLoads() {
        Runtime runtime =Runtime.getRuntime(); // 获取当前程序的运行进对象
        Process process = null; //声明处理类对象
        String line = null; //返回行信息
        InputStream is = null; //输入流
        InputStreamReader isr = null;// 字节流
        BufferedReader br = null;
        String ip = "192.168.88.124";
        boolean res = false;// 结果
        try {
            process =runtime.exec("ping " + ip); // PING

            is =process.getInputStream(); // 实例化输入流
            isr = new InputStreamReader(is);// 把输入流转换成字节流
            br = new BufferedReader(isr);// 从字节中读取文本
            while ((line= br.readLine()) != null) {
                if(line.contains("TTL")) {
                    res= true;
                    break;
                }
            }
            is.close();
            isr.close();
            br.close();
            if (res){
                System.out.println("ping通  ...");

            } else{
                System.out.println("ping不通...");
            }
        } catch (IOException e) {
            System.out.println(e);
            runtime.exit(1);
        }
    }

    //接口请求
    @Test
    public void test() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","application/json;");
        headers.set("Content-Type","application/json;charset=utf-8;");
        //放入body中的json参数
        String url = "https://ai075.ciopaas.com/api/login";
        String urllist = "https://ai075.ciopaas.com/api/crmList";
        JSONObject jsonobj =new JSONObject(true);
        jsonobj.put("username", "sc944659");
        jsonobj.put("password", "sc944659");
        jsonobj.put("from", "2");
        String md5 = DigestUtils.md5DigestAsHex(jsonobj.toString().getBytes()).substring(1,9);
        JSONObject obj =new JSONObject(true);
        obj.put("username", "sc944659");
        obj.put("password", "sc944659");
        obj.put("from", "2");
        obj.put("url", md5);
        HttpEntity<JSONObject> request = new HttpEntity<>(obj, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        String body = responseEntity.getBody();
        //{"code":0,"data":{"user_name":"sc944659","user_sn":"SYSUSER|1906478d5e50f3a6bc931081dd36d520","team_name":"\u4eba\u5de5\u667a\u80fd\u5ba2\u6237\u7ec4","expired":"2020-12-31","ai_count":4,"caller_group":"944659","api_key":"SvkvprD8","parent_sn":null,"api_key_expire":1605604942},"msg":"login success"}
        JSONObject jsonKEY = JSONObject.parseObject(body);
        if(!jsonKEY.get("msg").equals("login success")){
            System.err.println("请求秘钥失败");
        }else {
            JSONObject qqcslist = new JSONObject(true);
            qqcslist.put("api_key", JSONObject.parseObject(jsonKEY.get("data") + "").get("api_key"));
            qqcslist.put("user_sn", JSONObject.parseObject(jsonKEY.get("data") + "").get("user_sn"));
            qqcslist.put("url", md5);
            qqcslist.put("from", "2");
            qqcslist.put("start_create_at", "2020-11-01");
            qqcslist.put("end_create_at", "2020-11-17");
            HttpEntity<JSONObject> request1 = new HttpEntity<>(qqcslist, headers);
            ResponseEntity<String> responseL = restTemplate.exchange(urllist, HttpMethod.POST, request1, String.class);
            JSONObject jall = JSONObject.parseObject(responseL.getBody());
            JSONArray jo = JSONArray.parseArray(JSONObject.parseObject("" + jall.get("data")).get("list") + "");
            List<Map> maps = jo.toJavaList(Map.class);
            maps.forEach( p->{
                System.err.println("connected_at"+p.get("connected_at"));//接入时间
                System.err.println("is_transfertoagent"+p.get("is_transfertoagent"));//1是转人工，0是未转人工   如果是0就算是智能解答数
            });
        }
    }

    }




