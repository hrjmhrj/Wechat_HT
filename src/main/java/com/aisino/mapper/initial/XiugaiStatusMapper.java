package com.aisino.mapper.initial;


import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@DS("slave")
public interface XiugaiStatusMapper {

    void xiugaiStatus(String djhm);


    void updateFPZL(String ohid);

    List<Map<String, String>> selectlineByohid(String ohid);

    List<Map<String, String>> selectohidList(String ohid);

   List<Map<String,String>> selectXFXX(String id);

    List<Map<String, String>> selectXFXXID(String id);

    List<Map<String,String>>  selectXFXXIDList(List<Map<String, String>> list);

    void deleteXF(List<Map<String, String>> list);

    void addXF(List<Map<String, String>> list);

    List<Map<String,String>> selectallXFXX();

    List<Map<String, String>> selectheadByohid(String id);

    String selectchucuo(String ohid);

}
