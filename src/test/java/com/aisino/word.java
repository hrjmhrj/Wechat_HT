package com.aisino;


import com.aspose.cells.License;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


/**
 * @ClassName test
 * @Date 2020/4/3
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class word {

    @Test
    public void testredis() {
        //Jedis jedis = new Jedis("39.97.74.131", 6379);
        //String aa = jedis.get("a");
        //System.out.println(aa);
    }



    @Test
    public void Test1(){
        String version = SpringVersion.getVersion();
        String version1 = SpringBootVersion.getVersion();
        System.out.println(version);
        System.out.println(version1);
    }

    //word转换成PDF
    @Test
    public void Test2(){
        String word = "D:\\file\\bf08565fada2410bb9f4202db730d332.doc";
        String pdf = "D:\\file\\bf08565fada2410bb9f4202db730d332.pdf";
        wToPdfChange(word, pdf);
    }



    public static void wToPdfChange(String wordFile,String pdfFile){//wordFile word 的路径  //pdfFile pdf 的路径
        ActiveXComponent app = null;
        System.out.println("开始转换...");
        // 开始时间
        // long start = System.currentTimeMillis();
        try {
            // 打开word
            app = new ActiveXComponent("Word.Application");
            // 获得word中所有打开的文档
            Dispatch documents = app.getProperty("Documents").toDispatch();
            // 打开文档
            Dispatch document = Dispatch.call(documents, "Open", wordFile, false, true).toDispatch();
            // 如果文件存在的话，不会覆盖，会直接报错，所以我们需要判断文件是否存在
            File target = new File(pdfFile);
            if (target.exists()) {
                target.delete();
            }
            System.out.println("另存为: " + pdfFile);
            Dispatch.call(document, "SaveAs", pdfFile, 17);
            // 关闭文档
            Dispatch.call(document, "Close", false);
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("转换失败"+e.getMessage());
        }finally {
            // 关闭office
            app.invoke("Quit", 0);
        }
    }

    //excel转换成PDF
    @Test
    public void Test4(){
        String excel = "D:\\file\\00d34297b7a54d6dbc7ee816bb7065d5.xls";
        String pdf = "D:\\file\\00d34297b7a54d6dbc7ee816bb7065d5.pdf";
        excelToPdf(excel, pdf);
    }

    public static void excelToPdf(String inPath, String outPath) {
        // 验证License
        if (!getLicense()) {
            return;
        }
        try {
            long old = System.currentTimeMillis();
            Workbook wb = new Workbook(inPath); // 原始excel路径
            File pdfFile = new File(outPath); // 输出路径
            FileOutputStream fileOS = new FileOutputStream(pdfFile);
            wb.save(fileOS, SaveFormat.PDF);
            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old)) + "秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = Test.class.getClassLoader().getResourceAsStream("\\license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }








    @Test
    public void Test3(){
        System.out.println(System.getProperty("java.library.path"));
        String word = "D:\\file\\bf08565fada2410bb9f4202db730d332.doc";
        String pdf = "D:\\file\\bf08565fada2410bb9f4202db730d332.pdf";
        boolean res = Word2PdfJacobUtil.word2PDF(word, pdf);
        System.out.println(res);
    }


    public static class Word2PdfJacobUtil {

        /* 转PDF格式值 */
        private static final int wdFormatPDF = 17;
        /**
         * Word文档转换
         *
         * @param inputFile
         * @param pdfFile
         */
        public static boolean word2PDF(String inputFile, String pdfFile) {
            ComThread.InitMTA(true);
            long start = System.currentTimeMillis();
            ActiveXComponent app = null;
            Dispatch doc = null;
            try {
                app = new ActiveXComponent("Word.Application");// 创建一个word对象
                app.setProperty("Visible", new Variant(false)); // 不可见打开word
                app.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏
                Dispatch docs = app.getProperty("Documents").toDispatch();// 获取文挡属性

                System.out.println("打开文档 >>> " + inputFile);
                // Object[]第三个参数是表示“是否只读方式打开”
                // 调用Documents对象中Open方法打开文档，并返回打开的文档对象Document
                doc = Dispatch.call(docs, "Open", inputFile, false, true).toDispatch();
                System.out.println("转换文档 [" + inputFile + "] >>> [" + pdfFile + "]");
                // 调用Document对象的SaveAs方法，将文档保存为pdf格式
                // word保存为pdf格式宏，值为17
                Dispatch.call(doc, "SaveAs", pdfFile, wdFormatPDF);// word保存为pdf格式宏，值为17

                long end = System.currentTimeMillis();

                System.out.println("用时：" + (end - start) + "ms.");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("========Error:文档转换失败：" + e.getMessage());
            } finally {
                Dispatch.call(doc, "Close", false);
                System.out.println("关闭文档");
                if (app != null)
                    app.invoke("Quit", new Variant[] {});
                // 如果没有这句话,winword.exe进程将不会关闭
                ComThread.Release();
                ComThread.quitMainSTA();
            }
            return false;
        }
    }




}

