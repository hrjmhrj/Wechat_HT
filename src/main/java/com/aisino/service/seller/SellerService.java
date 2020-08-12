package com.aisino.service.seller;

import com.aisino.entity.system.Json;
import com.alibaba.fastjson.JSON;

import java.util.Map;

public interface SellerService {
    Json selectSellerList(Map<String,Object>map);
    Json selectSellerByUUID(Map<String,Object>map);
    Json selectUersByUUID(Map<String,Object>map);
    Json selectZJBList(Map<String,Object>map);
 }
