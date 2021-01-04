package com.test.mapper.initial;

import com.test.entity.initial.XfxxDZDHYHZH;
import com.baomidou.dynamic.datasource.annotation.DS;

import java.util.HashMap;

@DS("slave")
public interface XfxxDZDHYHZHMapper {
    int insert(XfxxDZDHYHZH record);

    int insertSelective(HashMap<String, String> hm);

    /**
     * 删除销方地址电话银行账号
     * @param xfjs
     * @return
     */
    int deleteByxfjs(String xfjs);

    /**
     * 查询销方信息地址电话银行账号
     * @param hm
     * @return
     */
    XfxxDZDHYHZH selectByPrimaryKey(HashMap<String, String> hm);

    /**
     * 修改销方id时，同时修改销方信息地址电话银行账号表中销方id
     * @param hashMap
     * @return
     */
    int updatexiaofangid(HashMap<String, String> hashMap);
}