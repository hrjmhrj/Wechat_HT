package com.aisino.mapper.seller;

import com.aisino.entity.initial.TPeizhi;

import java.util.HashMap;
import java.util.List;

public interface PeiZhiMapper {
    List<TPeizhi> selectOnePeiZhi();

    List<TPeizhi> selectPeiZhiByXiaoFang(String XFSH);

    /**
     * 根据ID删除配置
     * @param UUID
     * @return
     */
    int deleteById(String UUID);

    /**
     * 新增配置表数据
     * @param hm
     * @return
     */
    int addPeizhi(HashMap<String, String> hm);

    /**
     * 修改配置表
     * @param hm
     * @return
     */
    int updatePeizhi(HashMap<String, String> hm);
}
