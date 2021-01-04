package com.test.mapper.yanshimapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@DS("slave")
public interface yanshiMapper {


    List<Map<String, String>> selectYanshidata(Map<String, String> hm);
}
