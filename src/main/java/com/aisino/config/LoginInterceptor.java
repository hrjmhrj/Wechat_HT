package com.aisino.config;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 拦截器
 * @author yc
 */
public class LoginInterceptor implements HandlerInterceptor{
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

	/**
	 * 存储token 以及当前token的接口权限 和 过期时间
	 */
	public static Map<String,Map<String,Object>> mm = new ConcurrentSkipListMap<>();

	/**
	 * 存储userId 和 对应的token
	 */
	public static Map<String,String> ms = new ConcurrentSkipListMap<>();

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		//当前token
		String token = request.getHeader("gyxxfpnekot");

		//当前路径
		String requestUrlString=request.getRequestURI();
		String urlPathStrings = "/"+requestUrlString.split("/aisino/")[1];
		//登出
		LOGGER.info("解析后路径 "+urlPathStrings+"当前路径"+token);
		if("/aisino/loginOut".equals(urlPathStrings)){
			//删除token
			ms.remove(mm.get(token).get("userid"));
			mm.remove(token);
			return false;
		}

		// 为手机端访问接口时
		// if("moble".equals(request.getHeader("requestTypeZls"))){
		// 	return true;
		// }
		// token为空或者不存在
		if(token == null || "".equals(token) || !mm.containsKey(token)){
			returnMessage(response,99999,"非法访问或登录过期，请登录。","确定退出","登录","error");
			return false;

		}
		if(!(Boolean) mm.get(token).get("flag")){
			returnMessage(response,77777,"你的账号在别处登录。","确定","确定","error");
			mm.remove(token);
			return false;
		}
		// token存在 判断是否过期
		Calendar nowData = Calendar.getInstance();
		Calendar addOne = Calendar.getInstance();
		addOne.setTime((Date)(mm.get(token).get("nowTime")));
		addOne.set(Calendar.HOUR_OF_DAY, addOne.get(Calendar.HOUR_OF_DAY) + 1);
		if(addOne.before(nowData)){
			returnMessage(response,50014,"你的登录已经过期，请重新登录。","确定登出","重新登录","warning");
			mm.remove(token);
			return false;
		}else{
			//判断是否有接口权限
			System.out.println(mm.get(token).get("requestResoult"));
			if(!((List)mm.get(token).get("requestResoult")).contains(urlPathStrings)){
				returnMessage(response,88888,"你没有访问该功能的权限。","确定","确定","warning");
				return false;
			}else{
				//刷新token的有效时间
				mm.get(token).put("nowTime",new Date());
			}
		}
		return true;
	}

	/**
     * 返回给前端标准回答
     * @param response
     * @throws IOException
     */
    private void returnMessage(HttpServletResponse response,Integer status,String msg,String title,String btnStr,String boxType) throws IOException {
    	Map<String, Object> map = new HashMap<>(2);
    	map.put("status", status);
    	map.put("msg", msg);
		map.put("title", title);
		map.put("btnStr", btnStr);
		map.put("boxType",boxType);
        response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject jo = (JSONObject) JSONObject.toJSON(map);
		out.write(jo.toJSONString());
        out.flush();
        out.close();
    }

}
