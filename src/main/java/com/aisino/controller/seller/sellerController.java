package com.aisino.controller.seller;


import com.aisino.entity.system.Json;
import com.aisino.entity.system.LayUIJson;
import com.aisino.service.seller.SellerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@RequestMapping("/aisino")
@Controller
public class sellerController {
    @Resource
    private SellerService sellerService;
   /* *//**
     * 查询销方列表
     * @param map
     * @return LayUIJson
     *//*
    @RequestMapping("/selectSellerList")
    @ResponseBody
    public LayUIJson selecSellerList(@RequestBody Map<String,Object> map){
        PageHelper.startPage((Integer)map.get("page"),(Integer)map.get("limit"));
        return returnLayuiJson(sellerService.selectSellerList(map));
    }
    *//**
     * 查询销方列表byUUID
     * @param map
     * @return Json
     *//*
    @RequestMapping("/selectSellerByUUID")
    @ResponseBody
    public Json selectSellerByUUID(@RequestBody Map<String,Object> map){
        return  sellerService.selectSellerByUUID(map);
    }*/
    /**
     *  处理查询结果集返回layui数据表格标准格式
     * @param json
     * @return
     */
    public static LayUIJson returnLayuiJson(Json json){
        LayUIJson result;
        if(json.isSuccess()){
            Page<Map<String, String>> list = (Page<Map<String, String>>) json.getObj();
            result = new LayUIJson("0","查询成功",list.getTotal(),list);
        }else{
            result = new LayUIJson("4002","查询失败",null,null);
        }
        return result;
    }
    /**
     * 查询授权用户
     * @param map
     * @return Json
     */
    @RequestMapping("/selectUersByUUID")
    @ResponseBody
    public Json selectUersByUUID(@RequestBody Map<String,Object> map){
        return  sellerService.selectUersByUUID(map);
    }
    /**
     * 查询授权用户列表
     * @param map
     * @return LayUIJson
     */
    @RequestMapping("selectZJBList")
    @ResponseBody
    public LayUIJson selectZJBList(@RequestBody Map<String,Object> map){
        PageHelper.startPage((Integer)map.get("page"),(Integer)map.get("limit"));
        return  returnLayuiJson(sellerService.selectZJBList(map));
    }


}
