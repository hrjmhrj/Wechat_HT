package com.test.mapper.initial;

import com.test.entity.initial.TPeizhi;
import com.baomidou.dynamic.datasource.annotation.DS;

@DS("slave")
public interface TPeizhiMapper {
    int deleteByPrimaryKey(String UUID);

    int insert(TPeizhi record);

    int insertSelective(TPeizhi record);

    TPeizhi selectByPrimaryKey(String UUID);

    int updateByPrimaryKeySelective(TPeizhi record);

    int updateByPrimaryKey(TPeizhi record);
}