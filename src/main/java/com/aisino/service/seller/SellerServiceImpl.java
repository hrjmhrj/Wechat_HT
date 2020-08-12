package com.aisino.service.seller;

import com.aisino.entity.system.Json;
import com.aisino.mapper.seller.SellerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class SellerServiceImpl implements SellerService {
    @Resource
    private SellerMapper sellerMapper;
    @Override
    public Json selectSellerList(Map<String, Object> map) {
        try {
            return  new Json(true,200,"查询销方信息成功！",sellerMapper.selectSellerList(map));
        }catch ( Exception e){
            System.out.print(e);
           return  new Json(false,4002,"查询销方信息失败！");
        }

    }

    @Override
    public Json selectSellerByUUID(Map<String, Object> map) {
        try {
            return  new Json(true,200,"查询销方信息成功！",sellerMapper.selectSellerByUUID(map));
        }catch ( Exception e){
            System.out.print(e);
            return  new Json(false,4002,"查询销方信息失败！");
        }
    }

    @Override
    public Json selectUersByUUID(Map<String, Object> map) {
        try {
            return  new Json(true,200,"查询用户信息成功！",sellerMapper.selectUersByUUID(map));
        }catch ( Exception e){
            System.out.print(e);
            return  new Json(false,4002,"查询用户信息失败！");
        }
    }

    @Override
    public Json selectZJBList(Map<String, Object> map) {
        try {
            return  new Json(true,200,"查询授权用户成功！",sellerMapper.selectZJBList(map));
        }catch ( Exception e){
            System.out.print(e);
            return  new Json(false,4002,"查询授权用户失败！");
        }
    }
}
