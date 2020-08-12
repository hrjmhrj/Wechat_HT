package com.aisino.mapper.yanshimapper;



import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface TestORCMapper {


    List<Map<String, String>> getList(@Param("orcid")String  orcid);
}
