package com.test.controller.yanshicontroller;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *@ClassName ExcelDataListener
 *@Author 691040038@qq.com
 *@Date 2019/12/25
 */
public class ModelExcelDataListener extends AnalysisEventListener<readExcelData> {

    private List<readExcelData> list = new ArrayList<>();

    @Override  //这个每一条数据解析都会来调用
    public void invoke(readExcelData excelData, AnalysisContext analysisContext) {
        //log.info("解析到一条数据：{}", JSON.toJSONString(excelData));
        list.add(excelData);
        System.out.println("读取到每一条数据"+list.size());
        System.out.println(list);
            System.out.println(list.get(0).getColumn1());
            System.out.println(list.get(0).getColumn2());
            System.out.println(list.get(0).getColumn3());
            System.out.println(list.get(0).getColumn4());
            list.clear();
    }

    @Override  //读取每一个sheet表 数据解析完成了 都会来调用
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("一共数据 : " + list.size());
        //log.info("解析完成");
    }

    public List<readExcelData> getList(){
        return list;
    }
    /**
     * 加上存储数据库
     */
    private void saveData() {
        System.out.println("{}条数据，开始存储数据库！"+ list.size());
        System.out.println("存储数据库成功！");
    }
}
