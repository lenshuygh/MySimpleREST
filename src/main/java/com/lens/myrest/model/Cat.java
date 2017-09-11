package com.lens.myrest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cat {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length=20)
	@NotNull
	private String name;

	@Column
	@NotNull
	private int age;

	public int getAge() {
		return age;
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Cat [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

	
}
