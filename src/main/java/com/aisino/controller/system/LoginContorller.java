package com.aisino.controller.system;

import com.aisino.config.LoginInterceptor;
import com.aisino.entity.system.Json;
import com.aisino.entity.system.Menu;
import com.aisino.mapper.system.LoginMapper;
import com.aisino.mapper.system.QuanXianMapper;
import com.aisino.util.jwt.MD5;
import com.aisino.util.jwt.RSAUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 登录
 * @author yc
 */
@RequestMapping("/aisino")
@Controller
public class LoginContorller {

	@Resource
    LoginMapper loginMapper;
	@Resource
	private QuanXianMapper quanXianMapper;


	/**
	 *
	 * @Title: login
	 * @Description: TODO(登录验证)
	 * @param
	 * @return void    返回类型
	 */
	@RequestMapping("/checkLogin")
	@ResponseBody
	public Json login(@RequestBody Map<String,String> map) {
		Json json;
		try {
			String username=map.get("username");
			String password = MD5.md5(RSAUtils.decryptBase64(map.get("password")));
			//用户名存在
			if(loginMapper.checkUsername(username)) {
				Map<String,Object>  user=loginMapper.checkUsernameAndPSW(username, password);
				if(user!=null &&user.size()>0) {
					Map<String,Object> rlt=new HashMap<>(2);
					rlt.put("name", user.get("name"));
					rlt.put("username", user.get("username"));
					rlt.put("userId", user.get("id"));
					//设置token,接口资源，有效时间
					String token = setRequestResoult(user.get("id").toString());
					rlt.put("nekot",token);
					json=new Json(true,"密码正确！",rlt);
				}else {
					json=new Json(false,"密码错误,请重新输入密码!","");
				}
			}else {
				json=new Json(false,"用户名不存在,请输入正确的用户名！","");
			}
		}catch (Exception e) {
			e.printStackTrace();
			json=new Json(false,"数据连接异常，请检查网络设置","");
		}
		return json;
	}

	/**
	 * 获取菜单
	 * @param map
	 * @return
	 */
	@RequestMapping("/getMenu")
	@ResponseBody
	public Json getMenu(@RequestBody Map<String,String> map){
		//获取用户资源
		List<Menu> listMenu=loginMapper.selectByNameAndPSW(map.get("user_id"));
		Map<String,Object> rlt=new HashMap<>();
		List<Menu> resultMap=new ArrayList();
		List<Menu> list_P=new ArrayList<>();
		//子菜单
		List<Menu> list_C=new ArrayList<>();
		if(listMenu!=null&&listMenu.size()>0) {
			for(Menu m:listMenu) {
				//为父级菜单
				if("0".equals(m.getParent_id())) {
					list_P.add(m);
				}else{
					list_C.add(m);
				}
			}
		}
		//父级遍历
		for(Menu mp:list_P) {
			List<Menu> list=new ArrayList();
			//子菜单
			for(Menu mc:list_C) {
				if(mc.getParent_id().equals(mp.getId())) {
					list.add(mc);
				}
			}
			mp.setMenu(list);
			resultMap.add(mp);
		}
		rlt.put("Menu", resultMap);
		Json json=new Json(true,"菜单查询成功",rlt);
		return json;
	}

	/**
	 * 登出
	 * @param request
	 * @return
	 */
	@RequestMapping("/loginOut")
	@ResponseBody
	public Json loginOut(HttpServletRequest request) {
		Json json;
		System.out.println(request.getSession().getAttribute("username"));
		request.getSession().invalidate();
		json = new Json(true, 0, "");
		return json;
	}

	/**
	 * 设置接口权限以及token和有效时间
	 */
	public String  setRequestResoult(String id){
		//获取接口资源
		List<String> funList = quanXianMapper.selectAllFunByUserName(id);
		//生成token
		String token = UUID.randomUUID().toString();
		//设置当前token的用户权限和时间
		Map<String,Object> mo = new HashMap<>();
		mo.put("requestResoult",funList);
		mo.put("nowTime",new Date());
		mo.put("flag",true);
		mo.put("userid",id);
		//保存
		LoginInterceptor.mm.put(token,mo);
		//判断是否已经存在
		if(LoginInterceptor.ms.containsKey(id)){
			LoginInterceptor.mm.get(LoginInterceptor.ms.get(id)).put("flag",false);
		}
		LoginInterceptor.ms.put(id,token);
		return token;
	}

	/**
	 * 定时删除过期token 以及 flag为false的token
	 * 从任务启动开始 间隔 两个小时执行一次
	 */
	@Scheduled(initialDelay = 5000, fixedDelay = 1000*60*60*2)
	public void ScheduledDeleteToken(){
		Iterator<Map.Entry<String, Map<String, Object>>> itermm = LoginInterceptor.mm.entrySet().iterator();
		while (itermm.hasNext()){
			Map.Entry<String, Map<String, Object>> entry =  itermm.next();
			if (!(Boolean) entry.getValue().get("flag")){
				// flag == false
				LoginInterceptor.ms.remove(entry.getValue().get("userid"));
				itermm.remove();
			}else{
				// token过期
				Calendar nowData = Calendar.getInstance();
				Calendar addOne = Calendar.getInstance();
				addOne.setTime((Date)(entry.getValue().get("nowTime")));
				addOne.set(Calendar.HOUR_OF_DAY, addOne.get(Calendar.HOUR_OF_DAY) + 1);
				if(addOne.before(nowData)){
					LoginInterceptor.ms.remove(entry.getValue().get("userid"));
					itermm.remove();
				}
			}

		}
	}
}
