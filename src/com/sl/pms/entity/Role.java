package com.sl.pms.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private String value;
	
	@OneToMany(targetEntity=User.class, cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="role")
	private Set<User> users=new HashSet<>();
	
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="roles")
	@JoinTable(
			name="privilege_role",joinColumns=@JoinColumn(name="privilege_id"),
			inverseJoinColumns=@JoinColumn(name="role_id")
			)
	private Set<Privilege> privileges=new HashSet<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Set<Privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
 
}
