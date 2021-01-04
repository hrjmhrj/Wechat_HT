package com.test.entity.system;

import java.io.Serializable;

public class Role implements Serializable {

	private static final long serialVersionUID = 926506375465061033L;
	private String id;
	private String name;
	private int sort;
	private String menu_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((menu_id == null) ? 0 : menu_id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + sort;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (menu_id == null) {
			if (other.menu_id != null)
				return false;
		} else if (!menu_id.equals(other.menu_id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sort != other.sort)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", sort=" + sort
				+ ", menu_id=" + menu_id + "]";
	}
	
}
