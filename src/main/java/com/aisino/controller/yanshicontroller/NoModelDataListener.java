package com.aisino.controller.yanshicontroller;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 *@ClassName ExcelDataListener
 *@Author 691040038@qq.com
 *@Date 2019/12/25
 */
public class NoModelDataListener extends AnalysisEventListener<Map<String, String>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoModelDataListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<Map<String, String>> list = new ArrayList<>();

    //读取每一行数据都会调用这个
    @Override
    public void invoke(Map<String, String> data, AnalysisContext context) {

        System.out.println("读取的数据");
        System.out.println(data);
        /*list.add(data);
        System.out.println("不创建对象读取到每一条数据"+list.size());
        System.out.println(list);

        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }*/
    }

    //单独读取表头信息
    @Override
    public void invokeHead(Map<Integer, CellData> headMap, AnalysisContext context) {
        System.out.println("表头信息");
        System.out.println(headMap);
    }


    //最后所有数据读取完后,才会调用
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        LOGGER.info("存储数据库成功！");
    }
}
