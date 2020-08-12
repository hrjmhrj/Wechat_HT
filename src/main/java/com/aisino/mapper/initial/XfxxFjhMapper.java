package com.aisino.mapper.initial;

import com.aisino.entity.initial.XfxxFjh;
import com.baomidou.dynamic.datasource.annotation.DS;

import java.util.HashMap;

@DS("slave")
public interface XfxxFjhMapper {

    int insertSelective(HashMap<String, String> hm);

    int deleteByPrimaryKey(String bumenid);

    XfxxFjh selectByPrimaryKey(HashMap<String, String> hm);

    int updatexiaofangid(HashMap<String, String> hashMap);
}