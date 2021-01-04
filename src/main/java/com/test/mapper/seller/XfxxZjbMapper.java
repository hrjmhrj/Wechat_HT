package com.test.mapper.seller;

import com.baomidou.dynamic.datasource.annotation.DS;

import java.util.HashMap;
import java.util.List;

@DS("slave")
public interface XfxxZjbMapper {
    /**
     * 解除用户与销方的关联
     * @param UUID
     * @return
     */
    int deleteByPrimaryKey(String UUID);

    /**
     * 关联用户与销方
     * @param hm
     * @return
     */
    int insertSelective(HashMap<String, String> hm);

    /**
     * 获取登陆用户绑定的销方列表
     * @param USERID
     * @return
     */
    List<HashMap<String,String>> getXfxxListByUser(String USERID);

    /**
     * 检查用户与销方是否已关联
     * @param hm
     * @return
     */
    HashMap<String,String> selectByUserIdAndXfxxId(HashMap<String, String> hm);

    /**
     * 当修改销方id时，销方关联表的销方id也修改
     * @param hm
     * @return
     */
    int updataXfxxId(HashMap<String, String> hm);
}
