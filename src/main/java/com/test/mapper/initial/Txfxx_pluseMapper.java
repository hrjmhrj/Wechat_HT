package com.test.mapper.initial;

import com.test.entity.initial.Txfxx_pluse;
import com.baomidou.dynamic.datasource.annotation.DS;

@DS("slave")
public interface Txfxx_pluseMapper {
    int deleteByPrimaryKey(String UUID);

    int insert(Txfxx_pluse record);

    int insertSelective(Txfxx_pluse record);

    Txfxx_pluse selectByPrimaryKey(String UUID);

    int updateByPrimaryKeySelective(Txfxx_pluse record);

    int updateByPrimaryKey(Txfxx_pluse record);
}