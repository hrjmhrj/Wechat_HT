package com.aisino.mapper.system;

import com.aisino.entity.system.Menu;
import com.github.pagehelper.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 资源管理的dao层
 * @author gaomin
 * @date 2018/12/19
 *
 */

public interface MenuMapper {
	
	/**
	 * 添加资源
	 * @param Menu
	 * @return int
	 */
	int insertMenu(Menu menu);
	
	/**
	 * 编辑资源
	 * @param Menu
	 * @return int
	 */
	int updateMenu(Menu menu);
	
	/**
	 * 查询所有资源
	 * @param 
	 * @return Menu
	 */
	Page<Map<String, String>> selectAllMenu();
	
	/**
	 * 查询所有菜单资源
	 * @param 
	 * @return Menu
	 */
	List<HashMap<String,String>> selectAllMarkMenu();
	
	/**
	 * 删除资源
	 * @param str
	 * @return int
	 */
	int deleteMenu(String str);

	/**
	 * 删除资源于角色的关系数据
	 * @param id
	 * @return
	 */
	int deleteMenuAndRole(String id);

	/**
	 * 查询按钮
	 * @param id
	 * @return
	 */
	List<String> selectAllBtnById(String id);
}
