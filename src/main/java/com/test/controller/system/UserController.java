package com.test.controller.system;

import com.test.entity.system.Json;
import com.test.entity.system.LayUIJson;
import com.test.entity.system.User;
import com.test.mapper.system.UserMapper;
import com.test.util.json.JsonKeyUtil;
import com.test.util.jwt.MD5;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 用户管理的Controller层
 * @author gaomin
 * @date 2018/12/19
 *
 */

@RequestMapping("/test")
@Controller
public class UserController {

	@Resource
    UserMapper userMapper;

	@RequestMapping("/saveUser")
	@ResponseBody
	public Json insertUser(@RequestBody Map<String,String> map){
		Json json = new Json();
		try{
			String USERNAME = map.get("USERNAME");
			String PASSWORD = map.get("PASSWORD");
			String NAME = map.get("NAME");
			String COMPANY_ID = map.get("COMPANY_ID");
			String COMPANY_NAME = map.get("COMPANY_NAME");
			String ID = UUID.randomUUID().toString();
			User user = new User();
			user.setId(ID);
			user.setUsername(USERNAME);
			user.setPassword(MD5.md5(PASSWORD));
			user.setName(NAME);
			user.setCompany_id(COMPANY_ID);
			user.setCompany_name(COMPANY_NAME);
			int checkUserName = userMapper.checkUserName(USERNAME);
			if(checkUserName>0) {
				json.setSuccess(false);
				json.setStatus(0);
				json.setMsg("该用户已存在！");
				return json;
			}
			int n = userMapper.insertUser(user);
			if(n==0){
				json.setSuccess(false);
				json.setStatus(0);
				json.setMsg("添加用户失败！");
				return json;
			}else{
				json.setSuccess(true);
				json.setStatus(0);
				json.setMsg("添加用户成功！");
				json.setObj(JsonKeyUtil.transferJsonKey(user,false));
				return json;
			}
		}catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setStatus(1);
			json.setMsg("服务器异常！");
			return json;
		}
	}

	/**
	 *
	 * @file_name : UserController.java
	 * @method : checkUser
	 * @description : 检查用户名是否存在
	 * @author : YKQWILL
	 * @return
	 * @date : 2018年12月21日
	 * @return : Json
	 */
	@RequestMapping("/checkUser")
	@ResponseBody
	public Json checkUser(@RequestBody Map<String,String> map){
		Json json = new Json();
		try{
			String USERNAME = map.get("USERNAME");
			int n = userMapper.checkUserName(USERNAME);
			if(n==0){
				json.setSuccess(true);
			}else{
				json.setSuccess(false);
				json.setStatus(0);
				json.setMsg("该用户已存在！");
			}
		}catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setStatus(1);
			json.setMsg("服务器异常！");
		}
		return json;
	}

	/**
	 *
	 * @file_name : UserController.java
	 * @method : getUserList
	 * @description : 展示user的列表
	 * @author : YKQWILL
	 * @return
	 * @date : 2018年12月21日
	 * @return : Json
	 */
	@RequestMapping("/getUserList")
	@ResponseBody
	public Object getUserList(@RequestBody Map<String,Object>map){
		PageHelper.startPage((Integer)map.get("page"),(Integer)map.get("limit"));
		System.out.println(map);
		Page<Map<String, String>> selectUsers = userMapper.selectUsers(map);
		//System.out.println(selectUsers);
		LayUIJson result=new LayUIJson("0","查询成功",(long)selectUsers.getTotal(),selectUsers);
		return result;
	}

	/**
	 * @file_name : UserController.java
	 * @method : editUser
	 * @description : 修改用户信息
	 * @author : YKQWILL

	 * @return
	 * @date : 2018年12月21日
	 * @return : Json
	 */
	@RequestMapping("/editUser")
	@ResponseBody
	public Json editUser(@RequestBody Map<String,String> map){
		Json json = new Json();
		try{
			String ID = map.get("ID");
			String NAME = map.get("NAME");
			String COMPANY_ID = map.get("COMPANY_ID");
			String COMPANY_NAME = map.get("COMPANY_NAME");
			String PASSWORD = map.get("PASSWORD");
			User user = new User();
			user.setId(ID);
			user.setName(NAME);
			user.setPassword(StringUtil.isNotEmpty(PASSWORD)? MD5.md5(PASSWORD):"");
			user.setCompany_id(COMPANY_ID);
			user.setCompany_name(COMPANY_NAME);
			int checkUserId = userMapper.checkUserId(ID);
			if(checkUserId<1) {
				json.setSuccess(false);
				json.setStatus(0);
				json.setMsg("该用户不存在！");
				return json;
			}
			int n = userMapper.updateUser(user);
			if(n==0){
				json.setSuccess(false);
				json.setStatus(0);
				json.setMsg("修改用户信息失败！");
			}else{
				json.setSuccess(true);
				json.setStatus(0);
				json.setMsg("修改用户信息成功！");
			}
		}catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setStatus(1);
			json.setMsg("服务器异常！");
		}
		return json;
	}

	/**
	 * @file_name : UserController.java
	 * @method : deleteUser
	 * @description : 删除用户
	 * @author : YKQWILL

	 * @return
	 * @date : 2018年12月21日
	 * @return : Json
	 */
	@RequestMapping("/deleteUser")
	@ResponseBody
	public Json deleteUser(@RequestBody Map<String,String> map){
		Json json = new Json();
		try{
			String ID = map.get("ID");
			int checkUserId = userMapper.checkUserId(ID);
			if(checkUserId<1) {
				json.setSuccess(false);
				json.setStatus(1);
				json.setMsg("该用户不存在！");
				return json;
			}
			int n = userMapper.deleteUser(ID);
			if(n==0){
				json.setSuccess(false);
				json.setStatus(1);
				json.setMsg("删除用户失败！");
			}else{
				json.setSuccess(true);
				json.setStatus(1);
				json.setMsg("删除用户成功！");
			}
		}catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setStatus(1);
			json.setMsg("服务器异常！");
		}
		return json;
	}

	/**
	 * @file_name : UserController.java
	 * @method : getRoles
	 * @description : 获得角色列表
	 * @author : YKQWILL
	 * @return
	 * @date : 2018年12月21日
	 * @return : Json
	 */
	@RequestMapping("/getRoles")
	@ResponseBody
	public Json getRoles() {
		Json json = new Json();
		try {
			List<Map<String, String>> roleLists = userMapper.getRoleLists();
			json.setSuccess(true);
			json.setObj(roleLists);
		}catch(Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setStatus(1);
			json.setMsg("服务器异常！");
		}
		return json;
	}

	/**
	 * @file_name : UserController.java
	 * @method : saveUserRole
	 * @description : 保存用户和role的关系
	 * @author : YKQWILL
	 * @return
	 * @date : 2018年12月21日
	 * @return : Json
	 */
	@RequestMapping("/saveUserRole")
	@ResponseBody
	public Json saveUserRole(@RequestBody Map<String,String> map) {
		String role = map.get("ROLE_ID");
		String ID = map.get("ID");
		Json json = new Json();
		if(StringUtils.isBlank(role)) {
			json.setSuccess(false);
			json.setMsg("请选择角色！");
			return json;
		}
		String[] roles = role.split(",");
		try {
			int n = userMapper.deleteUserRole(ID);
			int count = 0;
			for (int i = 0; i < roles.length; i++) {
				String role_id = roles[i];
				count += userMapper.saveUserRole(ID,role_id);
			}
			if(count>0) {
				json.setSuccess(true);
				json.setMsg("保存用户角色成功！");
			}else {
				json.setSuccess(false);
				json.setMsg("保存用户角色失败！");
			}
		}catch(Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setStatus(1);
			json.setMsg("服务器异常！");
		}
		return json;
	}

	/**
	 * @file_name : UserController.java
	 * @method : checkUserPwd
	 * @description : 检测原密码
	 * @author : YKQWILL
	 * @date : 2019年1月2日
	 * @return : void
	 */
	@RequestMapping("/checkUserPwd")
	@ResponseBody
	public Json checkUserPwd(@RequestBody Map<String,String> map) {
		String USERNAME = map.get("USERNAME");
		String PASSWORD = map.get("PASSWORD");
		User user = userMapper.checkUserPwd(USERNAME, MD5.md5(PASSWORD));
		Json json = new Json();
		if(user!=null) {
			json.setSuccess(true);
			json.setObj(user);
		}else {
			json.setSuccess(false);
			json.setMsg("原密码错误！");
		}
		return json;
	}
}
