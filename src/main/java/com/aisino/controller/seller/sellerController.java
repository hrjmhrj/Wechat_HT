package com.aisino.controller.seller;


import com.aisino.entity.system.Json;
import com.aisino.entity.system.LayUIJson;
import com.aisino.mapper.initial.XiugaiStatusMapper;
import com.aisino.service.seller.SellerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/aisino")
@Controller
public class sellerController {
    @Resource
    private SellerService sellerService;
    @Resource
    private XiugaiStatusMapper xiugaiStatusMapper;
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



    @RequestMapping("/xfglDate")
    @ResponseBody()
    public Object xfglDate(@RequestBody String id) {
        id = id.replace("=", "");
        System.out.println("id===" + id);
        return xiugaiStatusMapper.selectXFXX(id);
    }

    @RequestMapping("/xfglDa")
    @ResponseBody()
    public Object xfglDa(@RequestBody String id) {
        id = id.replace("=", "");
        System.out.println("id===" + id);
        List<Map<String, String>> xfxxidList = xiugaiStatusMapper.selectXFXXID(id);  //去t_xfxx_zjb表找到同USERID下所有的XFXXID
        System.out.println("xfxxidList==" + xfxxidList);   //xfxxidList==[{XFXXID=91}, {XFXXID=61}]
        if (xfxxidList.size() == 0) {
            return xiugaiStatusMapper.selectallXFXX();
        }
        return xiugaiStatusMapper.selectXFXXIDList(xfxxidList);  //去t_xfxx 查找uuid不在list的数据


    }

    @RequestMapping("/deleteXF")
    @ResponseBody()
    public void deleteXF(@RequestBody List<Map<String, String>> list) {
        System.out.println("list===" + list);
        xiugaiStatusMapper.deleteXF(list);
    }

    @RequestMapping("/addXF")
    @ResponseBody()
    public void addXF(@RequestBody List<Map<String, String>> list) {
        //  list===[{XFXXID=71, USERID=07779236-900e-4871-ab0c-69e1afa02c25},
        //  {XFXXID=ca4d372d-8151-42aa-8a20-515a6458c28c, USERID=07779236-900e-4871-ab0c-69e1afa02c25}]
        List list1 = new ArrayList();
        Map<String, String> map = null;
        for (int i = 0; i < list.size(); i++) {
            String UID = UUID.randomUUID().toString();
            map = list.get(i);
            map.put("UUID", UID);
            list1.add(map);
        }
        System.out.println("list===" + list1);
        xiugaiStatusMapper.addXF(list);
    }






}
