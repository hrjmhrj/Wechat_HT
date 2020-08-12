package com.aisino.mapper.system;

import com.aisino.entity.system.User;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 用户管理的dao层
 * @author gaomin
 * @date 2018/12/19
 *
 */

@DS("slave")
public interface UserMapper {
	
	/**
	 * 添加用户
	 * @param User
	 * @return int
	 */
	int insertUser(User user);

	/**
	 * 
	 * @file_name : UserMapper.java
	 * @method : checkUserName
	 * @description : 检查数据库是否已有该用户名
	 * @author : YKQWILL
	 * @param username
	 * @return
	 * @date : 2018年12月21日
	 * @return : int
	 */
	int checkUserName(String username);

	/**
	 * 
	 * @file_name : UserMapper.java
	 * @method : selectUsers
	 * @description : 获取user列表
	 * @author : YKQWILL
	 * @return
	 * @date : 2018年12月21日
	 * @return : List<User>
	 * @param map
	 */
	Page<Map<String, String>> selectUsers(Map<String, Object> map);

	/**
	 * 
	 * @file_name : UserMapper.java
	 * @method : 根据用户的id判断用户是否存在
	 * @description : 描述
	 * @author : YKQWILL
	 * @param id
	 * @return
	 * @date : 2018年12月21日
	 * @return : int
	 */
	int checkUserId(String id);

	/**
	 * @file_name : UserMapper.java
	 * @method : updateUser
	 * @description : 修改用户信息
	 * @author : YKQWILL
	 * @param user
	 * @return
	 * @date : 2018年12月21日
	 * @return : int
	 */
	int updateUser(User user);

	/**
	 * @file_name : UserMapper.java
	 * @method : deleteUser
	 * @description : 根据id删除用户
	 * @author : YKQWILL
	 * @param id
	 * @return
	 * @date : 2018年12月21日
	 * @return : int
	 */
	int deleteUser(String id);

	/**
	 * @file_name : UserMapper.java
	 * @method : getRoleLists
	 * @description : 获得角色列表
	 * @author : YKQWILL
	 * @return
	 * @date : 2018年12月21日
	 * @return : List<Map<String,String>>
	 */
	List<Map<String, String>> getRoleLists();

	/**
	 * @file_name : UserMapper.java
	 * @method : deleteUserRole
	 * @description : 根据id删除用户角色
	 * @author : YKQWILL
	 * @param id
	 * @return
	 * @date : 2018年12月21日
	 * @return : int
	 */
	int deleteUserRole(@Param("id") String id);

	/**
	 * @file_name : UserMapper.java
	 * @method : saveUserRole
	 * @description : 保存用户和角色关系
	 * @author : YKQWILL
	 * @param id
	 * @param role_id
	 * @return
	 * @date : 2018年12月21日
	 * @return : int
	 */
	int saveUserRole(@Param("id") String id, @Param("role_id") String role_id);

	/**
	 * 
	 * @file_name : UserMapper.java
	 * @method : checkUserPwd
	 * @description : 检测用户名和密码
	 * @author : YKQWILL
	 * @param username
	 * @param pwd
	 * @return
	 * @date : 2019年1月2日
	 * @return : int
	 */
	User checkUserPwd(@Param("username") String username, @Param("pwd") String pwd);
	
}
