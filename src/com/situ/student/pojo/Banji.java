package com.situ.student.pojo;

import java.io.Serializable;
import java.util.List;

public class Banji implements Serializable{

	private Integer id;
	private String name;
	public Banji() {
		super();
	}
	public Banji(String name) {
		super();
		this.name = name;
	}
	public Banji(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Banji [id=" + id + ", name=" + name + "]";
	}
	
}
