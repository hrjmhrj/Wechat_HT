package com.aisino.mapper.initial;

import com.aisino.entity.initial.TPeizhi;

public interface TPeizhiMapper {
    int deleteByPrimaryKey(String UUID);

    int insert(TPeizhi record);

    int insertSelective(TPeizhi record);

    TPeizhi selectByPrimaryKey(String UUID);

    int updateByPrimaryKeySelective(TPeizhi record);

    int updateByPrimaryKey(TPeizhi record);
}