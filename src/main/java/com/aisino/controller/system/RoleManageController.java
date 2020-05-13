package com.aisino.controller.system;

import com.aisino.entity.system.Json;
import com.aisino.entity.system.LayUIJson;
import com.aisino.mapper.system.QuanXianMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/aisino")
@Controller
public class RoleManageController {


    @Resource
    private QuanXianMapper quanXianMapper;

    // 查询角色
    @RequestMapping("/getRole")
    @ResponseBody
    public LayUIJson getRole() {
        List<Map<String, String>> list = quanXianMapper.selectAllRole();
        LayUIJson layUIJson = new LayUIJson("0", "获取角色成功", (long) 0, list);
        return layUIJson;

    }

    // 查询资源
    @RequestMapping("/getFun")
    @ResponseBody
    public LayUIJson getFun(@RequestBody Map<String, Object> map) {
        // 获取应该被选中的资源
        String fun = (String) map.get("MENU_ID");
        System.out.println(fun);
        String[] funsStrings;

        List<Map<String, String>> list = quanXianMapper.selectAllFun();
        // 遍历赋值每行资源的状态
        for (int i = 0; i < list.size(); i++) {
            if (fun != null) {
                funsStrings = fun.split(",");
                for (int j = 0; j < funsStrings.length; j++) {
                    if (funsStrings[j].equals(list.get(i).get("ID"))) {
                        list.get(i).put("IS_CHECK", "1");
                        break;
                    } else {
                        list.get(i).put("IS_CHECK", "0");
                    }
                }
            }
        }
        LayUIJson layUIJson = new LayUIJson("0", "获取资源成功", (long) 0, list);
        return layUIJson;

    }

    // 保存资源
    @RequestMapping("/saveFun")
    @ResponseBody
    public LayUIJson saveFun(@RequestBody Map<String, String> map) {
        System.out.println("更新数据" + map);
        int i = quanXianMapper.updateRoleFun(map);
        LayUIJson layUIJson = new LayUIJson("0", "保存资源成功", (long) 0, null);
        return layUIJson;
    }


    // 新增角色
    @RequestMapping("/insertNewRole")
    @ResponseBody
    public Json insertNewRole(@RequestBody Map<String, String> map) {
        Json json = new Json();
        try {
            System.out.println("插入数据" + map);
            map.put("ID", UUID.randomUUID().toString().replace("-", ""));
            int i = quanXianMapper.insertRole(map);
            if (i == 0) {
                json.setSuccess(true);
                json.setStatus(0);
                json.setMsg("添加角色失败！");
            } else {
                json.setSuccess(true);
                json.setStatus(0);
                json.setMsg("添加角色成功！");
            }
        } catch (Exception e) {
            json.setSuccess(true);
            json.setStatus(1);
            json.setMsg("服务器异常！");
        }
        return json;
    }

    // 删除角色
    @RequestMapping("/deleteRole")
    @ResponseBody
    public Json deleteRole(@RequestBody Map<String, String> map) {
        Json json = new Json();
        try {
            System.out.println("删除数据" + map);
            // 删除 角色以及对应表
            int i = quanXianMapper.deleteRole(map);
            int j = quanXianMapper.deleteRoleMenu(map);
            if (i == 0) {
                json.setSuccess(true);
                json.setStatus(0);
                json.setMsg("删除角色失败！");
            } else {
                json.setSuccess(true);
                json.setStatus(0);
                json.setMsg("删除角色成功！");
            }
        } catch (Exception e) {
            json.setSuccess(true);
            json.setStatus(1);
            json.setMsg("服务器异常！");
        }
        return json;
    }

    @RequestMapping("/updateRole")
    @ResponseBody
    public Json updateRole(@RequestBody Map<String, String> map) {
        Json json = new Json();
        try {
            System.out.println("修改数据" + map);
            // 删除 角色以及对应表
            int i = quanXianMapper.updateRole(map);
            if (i == 0) {
                json.setSuccess(true);
                json.setStatus(0);
                json.setMsg("修改角色失败！");
            } else {
                json.setSuccess(true);
                json.setStatus(0);
                json.setMsg("修改角色成功！");
            }
        } catch (Exception e) {
            json.setSuccess(true);
            json.setStatus(1);
            json.setMsg("服务器异常！");
        }
        return json;
    }


    @RequestMapping("/selectRoleMenu")
    @ResponseBody
    public LayUIJson selectRoleMenu(@RequestBody Map<String, String> map) {
        // 获取应该被选中的资源
        System.out.println(map);
        String roleID = map.get("roleID");
        // 查询有哪些资源
        List<String> selectRoleMenu = quanXianMapper.selectRoleMenu(roleID);
        System.out.println("原有功能更" + selectRoleMenu);
        List<Map<String, String>> list = quanXianMapper.selectAllFunXiaoXie(map);
        System.out.println(list);
        // 遍历赋值每行资源的状态
        for (int i = 0; i < list.size(); i++) {
            list.get(i).put("flag", "false");
            for (int j = 0; j < selectRoleMenu.size(); j++) {
                if (selectRoleMenu.get(j).equals(list.get(i).get("id"))) {
                    list.get(i).put("flag", "true");
                    break;
                }
            }
        }

		System.out.println("查询到结果"+list);
        LayUIJson layUIJson = new LayUIJson("0", "获取资源成功", (long) 0, list);
        return layUIJson;

    }

    //给角色授权资源
    @RequestMapping("/addRoleMenu")
    @ResponseBody
    public Json addRoleMenu(@RequestBody Map<String, String> map) {
        Json json = new Json();
        try {
            System.out.println(map);
            // 先删除原有资源
            int i = quanXianMapper.deleteRoleMenu(map);
            System.out.println(i);
            // 批量加入新的资源
            if (map.get("ids") != null && !"".equals(map.get("ids"))) {
                System.out.println(map.get("ids"));
                String ids[] = map.get("ids").split(",");
                for (int j = 0; j < ids.length; j++) {
                    System.out.println("加入");
                    Map<String, String> map2 = new HashMap<String, String>();
                    map2.put("ID", map.get("ID"));
                    map2.put("EMNUID", ids[j]);
                    int x = quanXianMapper.insertRoleMenu(map2);
                    System.out.println(x);
                }
            }

            json.setSuccess(true);
            json.setStatus(0);
            json.setMsg("授权成功！");
        } catch (Exception e) {
            json.setSuccess(true);
            json.setStatus(1);
            json.setMsg("服务器异常！");
        }
        return json;

    }
}
