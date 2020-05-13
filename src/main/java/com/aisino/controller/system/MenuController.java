package com.aisino.controller.system;

import com.aisino.entity.system.Json;
import com.aisino.entity.system.LayUIJson;
import com.aisino.entity.system.Menu;
import com.aisino.mapper.system.MenuMapper;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资源管理的Controller层
 * @author gaomin
 * @date 2018/12/19
 *
 */
@RequestMapping("/aisino")
@Controller
public class MenuController {

	@Resource
    MenuMapper menuMapper;

	/**
	 * 添加资源
	 * @param request
	 * @return Json
	 */

	@RequestMapping("/insertMenu")
	@ResponseBody
	public Json insertMenu(@RequestBody Menu menu){
		Json json = new Json();
		try{
			String id = String.valueOf(System.currentTimeMillis() / 1000);
			menu.setId(id);
			if(menu.getParent_id() == null || "".equals(menu.getParent_id())){
				menu.setParent_id("0");
			}
			int n = menuMapper.insertMenu(menu);
			if(n==0){
				json.setSuccess(true);
				json.setStatus(5001);
				json.setMsg("添加资源失败");
			}else{
				json.setSuccess(true);
				json.setStatus(0);
				json.setMsg("添加资源成功");
			}

		}catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(true);
			json.setStatus(50000);
			json.setMsg("服务器异常");
		}
		return json;
	}

	/**
	 * 编辑资源
	 * @param request
	 * @return Json
	 */

	@RequestMapping("/updateMenu")
	@ResponseBody
	public Json updateMenu(@RequestBody Menu menu){
		Json json = new Json();
		try{
			int n = menuMapper.updateMenu(menu);
			if(n==0){
				json.setSuccess(true);
				json.setStatus(5001);
				json.setMsg("更新资源失败！");
			}else{
				json.setSuccess(true);
				json.setStatus(0);
				json.setMsg("更新资源成功！");
			}

		}catch (Exception e) {
			json.setSuccess(true);
			json.setStatus(50000);
			json.setMsg("服务器异常！");
		}
		return json;
	}

	/**
	 * 查询所有资源
	 * @param map
	 * @return Object
	 */

	@RequestMapping("/selectAllMenu")
	@ResponseBody
	public Object selectAllMenu(){
		Page<Map<String, String>> headListMaps = menuMapper.selectAllMenu();
		LayUIJson result=new LayUIJson("0","查询成功",(long)headListMaps.getTotal(),headListMaps);
		return result;
	}


	/**
	 * 删除资源
	 * @return Json
	 */

	@RequestMapping("/deleteMenu")
	@ResponseBody
	public Json deleteMenu(@RequestBody Map<String,String> map){
		Json json = new Json();
		try{
			String id = map.get("id");
			int w = menuMapper.deleteMenuAndRole(id);
			int n = menuMapper.deleteMenu(id);
			if(n==0){
				json.setSuccess(true);
				json.setStatus(1);
				json.setMsg("删除资源失败");
			}else{
				json.setSuccess(true);
				json.setStatus(0);
				json.setMsg("删除资源成功");
			}
		}catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(true);
			json.setStatus(1);
			json.setMsg("服务器异常");
		}
		return json;
	}

	/**
	 * 查询所有菜单资源
	 * @param map
	 * @return Object
	 */

	@RequestMapping("/selectAllMarkMenu")
	@ResponseBody
	public Object selectAllMarkMenu(){
		List<HashMap<String,String>> headListMaps = menuMapper.selectAllMarkMenu();
		Json json = new Json();
		json.setSuccess(true);
		json.setStatus(0);
		json.setMsg("查询成功！");
		json.setObj(headListMaps);
		return json;
	}

	/**
	 * 查询按钮权限列表
	 * @param map
	 * @return
	 */
	@RequestMapping("/selectAllBtnById")
	@ResponseBody
	public Object selectAllBtnById(@RequestBody Map<String,String> map){
		try{
			List<String> ls = menuMapper.selectAllBtnById(map.get("id"));
			Json json = new Json(true,"查询成功",ls);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			return new Json(false,5001,"查询按钮权限失败");
		}
	}
}

