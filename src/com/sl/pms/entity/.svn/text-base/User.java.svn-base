package com.sl.pms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
/**
 * 一个用户有一个角色，一个角色有多个用户，	User为多
 * @author carols
 *
 */
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;
	@Column
	public String uid;
	@Column
	public String username;
	@Column
	public String password;
	
	@ManyToOne(targetEntity=Role.class,cascade=CascadeType.ALL)
	@JoinColumn(name="role_id", referencedColumnName="id")
	public Role role;
	
	@Column
	public String department;
	@Column
	public String position;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
