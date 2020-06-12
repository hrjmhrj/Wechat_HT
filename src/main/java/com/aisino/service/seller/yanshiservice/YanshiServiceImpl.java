package com.aisino.service.seller.yanshiservice;


import com.aisino.entity.system.Json;
import com.aisino.mapper.yanshimapper.yanshiMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class YanshiServiceImpl implements yanshiService {
    @Resource
    private yanshiMapper yanshiMapper;
    @Override
    public List<Map<String, String>> selectYanshidata(Map<String, String> hm) {
        try {
            List<Map<String,String>> list=yanshiMapper.selectYanshidata(hm);
           return  list;
        } catch (Exception e) {
            return  null;
        }
    }
}
