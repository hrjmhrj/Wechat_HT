package com.aisino.controller.yanshicontroller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("/aisino")
@RestController
public class poiController {
    //先要有个路径
    static String path= "C:/tmp/";

    //poi导出数据
    @RequestMapping("poiExport")
    public void poiExport(HttpServletResponse response,@RequestBody List<Map<String, String>> list) throws Exception {
        System.out.println("poi导出list的值");
        System.out.println(list);
        //1，创建一个工作薄
        //Workbook wordkbook =new HSSFWorkbook();
        XSSFWorkbook  wordkbook = new XSSFWorkbook();
        //表名
        Sheet sheet=wordkbook.createSheet("灰灰统计表");
        Row row1=sheet.createRow(0);
        int a=0;
        for(String key: list.get(0).keySet()){
            System.out.println("key的值");
            System.out.println(key);
            Cell cell = row1.createCell(a);
            cell.setCellValue(key);
            a++;
        }
        for(int i=0;i<list.size();i++){
            int b=0;
            Row row=sheet.createRow(i+1);
            for(String value: list.get(i).values()){
                Cell cell = row.createCell(b);
                cell.setCellValue(value);
                b++;
            }
        }
        //生成到本地
        //FileOutputStream fileOutputStream = new FileOutputStream(path + "灰灰统计表07.xlsx");
        //wordkbook.write(fileOutputStream);
        //wordkbook.close();
        //返回前端下载
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment");
        wordkbook.write( response.getOutputStream());
        wordkbook.close();

    }




    //poi读取数据
    @RequestMapping("/poiReadExcel")
    @ResponseBody
    public void poiReadExcel(@RequestParam("updateFile") MultipartFile file) throws IOException {
        System.out.println("poi读取Excel");
        InputStream xlsFile=file.getInputStream();
        // 获得工作簿对象
        XSSFWorkbook workbook = new XSSFWorkbook(xlsFile);
        // 获得所有工作表,0指第一个表格
        XSSFSheet sheet = workbook.getSheetAt(0);

        // 获得行数
        int rows = sheet.getLastRowNum()+1;
        // 获得列数
        int cells = sheet.getRow(0).getPhysicalNumberOfCells();
        // 读取数据
        for (int row = 0; row < rows; row++) {
            //获取行,行号作为参数传递给getRow方法,第一行从0开始计算
            XSSFRow r = sheet.getRow(row);
            //遍历列
            List<String> rowdatalist=new ArrayList<>();
            for (int col = 0; col < cells; col++) {
                rowdatalist.add(r.getCell(col).toString());
            }
            System.out.println("每一行的数据");
            System.out.println(rowdatalist);

        }
    }












}
