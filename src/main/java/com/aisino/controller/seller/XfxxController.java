package com.aisino.controller.seller;

import com.aisino.entity.system.Json;
import com.aisino.entity.system.LayUIJson;
import com.github.pagehelper.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * 选择管理的公司
 */
@RequestMapping("/aisino/orderhead")
@RestController
public class XfxxController {




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
