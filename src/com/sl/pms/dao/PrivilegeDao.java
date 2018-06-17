package com.sl.pms.dao;

import java.util.List;

import com.sl.pms.entity.Privilege;


public interface PrivilegeDao extends BaseDao<Privilege> {
	
	public  List<Privilege> findbyObject(Privilege p);
	
	public List<Privilege> findByName(String name);

}
