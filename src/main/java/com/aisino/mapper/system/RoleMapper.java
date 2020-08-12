package com.aisino.mapper.system;

import com.aisino.entity.system.Role;
import com.baomidou.dynamic.datasource.annotation.DS;

/**
 * 角色管理的dao层
 * @author gaomin
 * @date 2018/12/19
 *
 */

@DS("slave")
public interface RoleMapper {
	
	/**
	 * 添加角色
	 * @param Role
	 * @return int
	 */
	int insertRole(Role role);
	
}
