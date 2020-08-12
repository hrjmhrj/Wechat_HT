package com.aisino.mapper.initial;

import com.baomidou.dynamic.datasource.annotation.DS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DS("slave")
public interface TZLSZpglbMapper {


    int changeDataStatus(String stauts, String i_feeid);

    List<String> selectAllItem();

    /**
     * 查询红冲数据
     * @param hm
     * @return
     */
    List<HashMap<String, String>> selectHongChongByFilter(HashMap<String, String> hm);

    /**
     * 查询开具当月水费发票金额
     * @return
     */
    Map<String, Object> countSFKJ(Map<String, Object> hm);

    /**
     * 查询未开具发票金额
     * @param map
     * @return
     */
    Map<String, Object> countWKJFPJE(Map<String, Object> map);

    /**
     * 查询补开以前月份水费发票金额
     * @return
     * @param map
     */
    Map<String, Object> countBKYQYFSFFPJE(Map<String, Object> map);

    /**
     *统计水费
     * @param map
     * @return
     */
    List<Map<String, Object>> countSF(Map<String, Object> map);
}