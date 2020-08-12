package com.aisino.controller.system;

import com.aisino.entity.system.Json;
import com.aisino.entity.system.Role;
import com.aisino.mapper.system.RoleMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 角色管理的Controller层
 * @author gaomin
 * @date 2018/12/19
 *
 */

@RequestMapping("/aisino")
@Controller
public class RoleController {

	@Resource
    RoleMapper roleMapper;

	@RequestMapping("/insertRole")
	@ResponseBody
	public Json insertRole(HttpServletRequest request){
		Json json = new Json();
		try{
			String id = "id";
			String name = "name";
			int sort = 1;
			String menu_id = "menu_id";
			Role role = new Role();
			role.setId(id);
			role.setName(name);
			role.setSort(sort);
			role.setMenu_id(menu_id);
			int n = roleMapper.insertRole(role);
			if(n==0){
				json.setSuccess(true);
				json.setStatus(0);
				json.setMsg("添加角色失败！");
			}else{
				json.setSuccess(true);
				json.setStatus(0);
				json.setMsg("添加角色成功！");
			}

		}catch (Exception e) {
			json.setSuccess(true);
			json.setStatus(1);
			json.setMsg("服务器异常！");
		}
		return json;
	}


}
