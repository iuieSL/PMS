package com.sl.pms.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.sl.pms.entity.Page;
import com.sl.pms.entity.Privilege;

public interface PrivilegeService {

	public boolean insert(Privilege privilege);
	
	public List<Privilege>  findAllPrivilege(Privilege p);
	
	public boolean delete(Privilege p);
	
	public Page<Privilege> count(Privilege p,Page<Privilege> page);
	
	//批量删除
	public boolean batchDelete(String listStr);
	
	public Privilege findById(String id);
	
	public boolean update(Privilege privilege);
	
	public List<Privilege> searchByName(String name);
	
	public  String findAllPage(JSONArray jsonarray);
}
