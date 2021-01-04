package com.test.controller.yanshicontroller;

import com.test.entity.system.Json;
import com.test.mapper.yanshimapper.TestORCMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RequestMapping("/connected")
@Controller
public class TestORCController {

    @Resource
    TestORCMapper testORCMapper;

    @RequestMapping("/transaction")
    @ResponseBody
    public Json selectAllMenu(@RequestBody Map<String,String>  hm) {
        try {
            System.err.println("orid="+hm.get("orid"));
            List<Map<String, String>> list = testORCMapper.getList(hm.get("orid"));
            System.err.println(list);
            return new Json(true, "查询成功", list);
        } catch (Exception e) {
            return new Json(false, "删除失败,数据库异常", "");
        }
    }

}