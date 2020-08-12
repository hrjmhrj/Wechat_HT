package com.aisino.entity.system;

import java.io.Serializable;

/**
 * FileName: JsonJ.java
 * @Description: layUI列表数据格式
 * All rights Reserved, Designed By JS-YFB
 * Copyright:   Copyright(C) 2017-2027
 * Company      JS-YFB LTD.
 * @author:     杨陈
 * @version     V1.0 
 * Createdate:  2018年5月29日 下午4:40:00
 *
 */
public class LayUIJson implements Serializable{
	
	private static final long serialVersionUID = -751365009761785376L;
	
	String status = "0";
	String msg = ""; 
	Long count = 0L;//数据条数
	Object data = null;
	
	
	
	public String getStatus() {
		return status;
	}
	public String getMsg() {
		return msg;
	}
	public Long getCount() {
		return count;
	}
	public Object getData() {
		return data;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "LayUIJson [status=" + status + ", msg=" + msg + ", count=" + count
				+ ", data=" + data + "]";
	}
	
	public LayUIJson(String status, String msg, Long count, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}
	public LayUIJson() {
		super();
	}
	
	
}
