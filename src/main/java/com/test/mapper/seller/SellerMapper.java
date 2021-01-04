package com.test.mapper.seller;

import com.github.pagehelper.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SellerMapper {
     Page<Map<String,Object>>selectSellerList(Map<String,Object> map);
     Map<String,Object>selectSellerByUUID(Map<String,Object> map);
     List<Map<String,Object>> selectUersByUUID(Map<String,Object> map);
     Page<Map<String,Object>>selectZJBList(Map<String,Object> map);
}
