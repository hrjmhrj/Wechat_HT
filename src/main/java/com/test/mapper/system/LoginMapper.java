package com.test.mapper.system;

import com.test.entity.system.Menu;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: LoginMapper
 * @Description: TODO(登录验证)
 * @author STIVEN
 * @date 2018年12月21日
 *
 */
@DS("slave")
public interface LoginMapper {
	/**
	 * 
	* @Title: checkUsername
	* @Description: TODO(用户验证)
	* @param @param username
	* @param @return    参数
	* @return boolean    返回类型
	 */
	boolean checkUsername(@Param("username") String username);
	/**
	 * 
	 * @Title: checkUsernameAndPSW
	 * @Description: TODO(验证用户和密码)
	 * @param @return    参数
	 * @return boolean    返回类型
	 */
	Map<String,Object>   checkUsernameAndPSW(@Param("username") String username, @Param("password") String password);
	/**
	 * 
	 * @Title: insertRole
	 * @Description: TODO(和资源获取)
	 * @param @param role
	 * @param @return    参数
	 * @return int    返回类型
	 */
	List<Menu> selectByNameAndPSW(String user_id);
	/**
	 * 
	* @Title: selectByUsernameAndPSW
	* @Description: TODO(通过用户名和密码查询用户中文名称)
	* @param @param username
	* @param @param password
	* @param @return    参数
	* @return Map<String,Object>    返回类型
	 */
    Map<String,Object>  selectByUsernameAndPSW(@Param("username") String username, @Param("password") String password);


}
