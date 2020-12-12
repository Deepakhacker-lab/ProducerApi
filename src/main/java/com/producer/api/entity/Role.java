package com.producer.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	 @Enumerated(EnumType.STRING)
	private roles name;



	public roles getname() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setname(roles role) {
		this.name = role;
	}
	public Role() {}

	public Role(roles name) {
		super();
		this.name = name;
	}
	
	
}
