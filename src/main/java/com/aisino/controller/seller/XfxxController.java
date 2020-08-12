package com.aisino.controller.seller;

import com.aisino.entity.system.Json;
import com.aisino.entity.system.LayUIJson;
import com.aisino.service.seller.SelectXfxxService;
import com.github.pagehelper.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * 选择管理的公司
 */
@RequestMapping("/aisino/orderhead")
@RestController
public class XfxxController {
    @Resource
    SelectXfxxService selectXfxxService;
    @RequestMapping("/getXfxxListByUser")
    public Json getXfxxListByUser(@RequestBody HashMap<String,Object> mapString){
        try{
            String USERID = (String) mapString.get("USERID");
            return new Json(true,"获取管理公司列表成功",selectXfxxService.getXfxxListByUser(USERID));
        }catch (Exception e){
            e.printStackTrace();
            return new Json(false,"获取管理公司列表失败，请稍后重试。","");
        }
    }


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






}
