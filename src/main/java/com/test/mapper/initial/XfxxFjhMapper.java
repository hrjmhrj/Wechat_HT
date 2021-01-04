package com.test.mapper.initial;

import com.test.entity.initial.XfxxFjh;

import java.util.HashMap;

public interface XfxxFjhMapper {

    int insertSelective(HashMap<String, String> hm);

    int deleteByPrimaryKey(String bumenid);

    XfxxFjh selectByPrimaryKey(HashMap<String, String> hm);

    int updatexiaofangid(HashMap<String, String> hashMap);
}