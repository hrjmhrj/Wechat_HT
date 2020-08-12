package com.aisino.mapper.system;

import com.baomidou.dynamic.datasource.annotation.DS;

import java.util.List;
import java.util.Map;

@DS("slave")
public interface QuanXianMapper {
	
	List<String>selectAllFunByUserName(String ID);
	
	List<Map<String, String>>selectAllRole();
	List<Map<String, String>>selectAllFun();
	
	List<Map<String, String>>selectAllFunXiaoXie(Map<String, String> map);
	
	List<String>selectAllFunPage();
	
	List<String>selectRoleMenu(String roleID);
	
	int updateRoleFun(Map<String, String> map);
	
	int insertRole(Map<String, String> map);
	
	int deleteRole(Map<String, String> map);
	
	int deleteRoleMenu(Map<String, String> map);
	
	int insertRoleMenu(Map<String, String> map);
	
	int updateRole(Map<String, String> map);

	public List<String>selectName(String username);

	List<String> selectYwUserFunList(String role_id);
}
