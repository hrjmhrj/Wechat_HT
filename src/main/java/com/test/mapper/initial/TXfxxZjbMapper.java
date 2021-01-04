package com.test.mapper.initial;

import com.test.entity.initial.TXfxxZjb;
import com.baomidou.dynamic.datasource.annotation.DS;

import java.util.HashMap;
import java.util.List;

@DS("slave")
public interface TXfxxZjbMapper {
    /**
     * 解除用户与销方的关联
     * @param UUID
     * @return
     */
    int deleteByPrimaryKey(String UUID);

    int insert(TXfxxZjb record);

    /**
     * 关联用户与销方
     * @param record
     * @return
     */
    int insertSelective(TXfxxZjb record);

    TXfxxZjb selectByPrimaryKey(String UUID);

    int updateByPrimaryKeySelective(TXfxxZjb record);

    int updateByPrimaryKey(TXfxxZjb record);

    /**
     * 获取登陆用户绑定的销方列表
     * @param USERID
     * @return
     */
    List<TXfxxZjb> getXfxxListByUser(String USERID);

    /**
     * 检查用户与销方是否已关联
     * @param hm
     * @return
     */
    TXfxxZjb selectByUserIdAndXfxxId(HashMap<String,String> hm);
}