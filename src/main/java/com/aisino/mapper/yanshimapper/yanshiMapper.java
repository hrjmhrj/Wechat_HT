package com.aisino.mapper.yanshimapper;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface yanshiMapper {


    List<Map<String, String>> selectYanshidata(Map<String, String> hm);
}
