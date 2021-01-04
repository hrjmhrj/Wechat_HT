package com.test.mapper.system;

import com.test.entity.system.Role;

/**
 * 角色管理的dao层
 * @author gaomin
 * @date 2018/12/19
 *
 */

public interface RoleMapper {
	
	/**
	 * 添加角色
	 * @param Role
	 * @return int
	 */
	int insertRole(Role role);
	
}
