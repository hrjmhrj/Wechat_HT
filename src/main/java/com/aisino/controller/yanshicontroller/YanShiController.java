package com.aisino.controller.yanshicontroller;

import com.aisino.entity.system.Json;
import com.aisino.service.seller.yanshiservice.yanshiService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ZShua
 */
@RequestMapping("/aisino")
@RestController
public class YanShiController {
    @Resource
    private yanshiService yanshiService;
    @Value("${file.defaultPath}")
    private String fileRootPath;
    /**
     * @param hm
     * @return
     */
    @RequestMapping("/selectYanshidata")
    @ResponseBody
    public Json selectYanshidata(@RequestBody Map<String, String> hm) {
        try {
           List<Map<String,String>> list= yanshiService.selectYanshidata(hm);
            return new Json(true, "查询成功",list );
        } catch (Exception e) {
            return new Json(false, "删除失败,数据库异常", "");
        }
    }

    @RequestMapping("/easyExcelReadModel")   //创建对象读取
    @ResponseBody
    public Json ModelExcelDataListener(@RequestParam("updateFile") MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), readExcelData.class, new ModelExcelDataListener()).sheet().doRead();
        return null;
    }

    @RequestMapping("/easyExcelRead")   //不创建对象读取
    @ResponseBody
    public void NoModelDataListener(@RequestParam("updateFile") MultipartFile file) throws IOException {
        //EasyExcel.read(file.getInputStream(), new NoModelDataListener()).sheet(1).doRead();   //sheet的序号代表读取第几个sheet表,下边从0 开始


        //================================下边是读取多个sheet表格=============
        ExcelReader excelReader = EasyExcel.read(file.getInputStream()).build();
        ReadSheet readSheet0 =EasyExcel.readSheet(0).registerReadListener(new NoModelDataListener()).build();
        ReadSheet readSheet1 =EasyExcel.readSheet(1).registerReadListener(new NoModelDataListener()).build();
        excelReader.read(readSheet0, readSheet1);
        excelReader.finish();
    }


    //easyExcel不创建对象导出
    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletResponse response, @RequestBody List<Map<String, String>> list) throws Exception {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            //将导出的数据直接保存到本地
            //String fileName = "C:\\tmp\\2222.xlsx";
            //EasyExcel.write(fileName).head(head()).sheet("模板").doWrite(dataList(list));
            EasyExcel.write(response.getOutputStream()).head(head()).sheet("模板").doWrite(dataList(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //不创建对象时,,下边是设置表头的
    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<>();
        String[] str=new String[] {"OHID","复核人","UPDATETIME","购方名称","购方税号","收款人","CREATETIME"};
        for(int a=0;a<str.length;a++){
            List<String> head0 = new ArrayList();
            head0.add(str[a]);
            list.add(head0);
        }
        return list;
    }
    private List<List<Object>> dataList(List<Map<String, String>> list) {
        List<List<Object>> returnlist = new ArrayList<>();
        for(int i=0 ;i<list.size();i++){
            List<Object> data = new ArrayList<>();
            for(String value :list.get(i).values()){
                data.add(value);
            }
            returnlist.add(data);
        }
        System.out.println("写入Excel的数据");
        System.out.println(returnlist);
        return returnlist;
    }


    ////easyexcel创建对象导出
    @RequestMapping("Write")
    public void ModelWiite(HttpServletResponse response, @RequestBody List<Map<String, String>> list) throws Exception {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            //String fileName = "C:\\tmp\\2222.xlsx";
            //EasyExcel.write(fileName,DownloadData.class).sheet("模板").doWrite(dataList(list));
            EasyExcel.write(response.getOutputStream(),DownloadData.class).sheet("模板").doWrite(dataList(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }







    @RequestMapping("/PDFdownload")
    @ResponseBody
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
        String name = new Long(System.currentTimeMillis()).toString() + ".X";
        int index = url.lastIndexOf("/");
        if(index > 0){
            name = url.substring(index + 1);
            if(name.trim().length()>0){
                return name;
            }
        }
        return name;
    }

}
