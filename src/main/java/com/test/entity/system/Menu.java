package com.test.entity.system;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Menu implements Serializable {
	
	private static final long serialVersionUID = 6246340927977088846L;
	private String id;
	private String name;
	private String url;
	private String parent_id;
	private int sort;
	private String mark;
	private String icon_url;
	
	private List<com.test.entity.system.Menu> Menu;

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}

	public void setMenu(List<com.test.entity.system.Menu> menu) {
		Menu = menu;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public String getParent_id() {
		return parent_id;
	}

	public int getSort() {
		return sort;
	}

	public String getMark() {
		return mark;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public List<com.test.entity.system.Menu> getMenu() {
		return Menu;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Menu menu = (Menu) o;
		return sort == menu.sort &&
				Objects.equals(id, menu.id) &&
				Objects.equals(name, menu.name) &&
				Objects.equals(url, menu.url) &&
				Objects.equals(parent_id, menu.parent_id) &&
				Objects.equals(mark, menu.mark) &&
				Objects.equals(icon_url, menu.icon_url) &&
				Objects.equals(Menu, menu.Menu);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, name, url, parent_id, sort, mark, icon_url, Menu);
	}

	@Override
	public String toString() {
		return "Menu{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", url='" + url + '\'' +
				", parent_id='" + parent_id + '\'' +
				", sort=" + sort +
				", mark='" + mark + '\'' +
				", icon_url='" + icon_url + '\'' +
				", Menu=" + Menu +
				'}';
	}
}
