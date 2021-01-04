package com.test.mapper.initial;

import com.test.entity.initial.TXfxx;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import java.util.Map;

@DS("slave")
public interface TXfxxMapper {
    int deleteByPrimaryKey(String UUID);

    int insert(TXfxx record);

    int insertSelective(TXfxx record);

    TXfxx selectByPrimaryKey(String UUID);

    int updateByPrimaryKeySelective(TXfxx record);

    int updateByPrimaryKey(TXfxx record);

    List<TXfxx> getXfxxList(@Param("ls") List<String> ls);

    Map<String, Object> getTxfxxByXfsh(String xfsh);
}