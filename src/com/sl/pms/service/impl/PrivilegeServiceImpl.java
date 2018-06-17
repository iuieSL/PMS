package com.sl.pms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sl.pms.dao.PrivilegeDao;
import com.sl.pms.entity.Page;
import com.sl.pms.entity.Privilege;
import com.sl.pms.service.PrivilegeService;
import com.sl.pms.util.Json;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

	@Autowired
	private PrivilegeDao privilegeDao;

	@Override
	public boolean insert(Privilege privilege) {
		if (privilegeDao.findbyObject(privilege).size() > 0)
			return false;
		else {
			return privilegeDao.insert(privilege);

		}
	}

	@Override
	public List<Privilege> findAllPrivilege(Privilege p) {
		// TODO Auto-generated method stub
		return privilegeDao.findAll(p);
	}

	@Override
	public boolean delete(Privilege p) {
		return this.privilegeDao.delete(p);
	}

	@Override
	public Page<Privilege> count(Privilege privilege, Page<Privilege> page) {
		return this.privilegeDao.count(privilege, page);
	}

	@Override
	public boolean batchDelete(String listStr) {

		String[] array = listStr.split(",");

		List<Integer> ids = new ArrayList<>();
		for (String s : array) {
			ids.add(new Integer(s));
		}
		return this.privilegeDao.batchDelete(ids, new Privilege());

	}

	@Override
	public Privilege findById(String id) {
		if (id != null) {
			return this.privilegeDao.findById(new Privilege(), new Integer(id));
		}
		return new Privilege();

	}

	@Override
	public boolean update(Privilege privilege) {
	  return	this.privilegeDao.update(privilege);
 
	}

	@Override
	public List<Privilege> searchByName(String name) {
		return this.privilegeDao.findByName(name);
	}

	@Override
	public String findAllPage(JSONArray jsonarray) {

		String sEcho = null;
		int iDisplayStart = 0;
		int iDisplayLength = 0;
		String sSearch = "";
		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject obj = (JSONObject) jsonarray.get(i);
			if (obj.get("name").equals("sEcho")) {
				sEcho = obj.get("value").toString();
			}
			if (obj.get("name").equals("iDisplayStart")) {
				iDisplayStart = (Integer) obj.get("value");
			}
			if (obj.get("name").equals("iDisplayLength")) {
				iDisplayLength = (Integer) obj.get("value");
			}
			if (obj.get("name").equals("sSearch"))
				sSearch = obj.get("value").toString();
		}

		Page<Privilege> page = new Page<>();
		page.setPageSize(iDisplayLength);
		page.setNextData(iDisplayStart);

		Privilege privilege = new Privilege();

		if (!sSearch.trim().equalsIgnoreCase("")) {
			privilege = (Privilege) Json.bindValue(privilege, sSearch);
		}
		page = this.privilegeDao.findAllPage(privilege, page);
		;

		JSONObject getObj = new JSONObject();
		getObj.put("sEcho", sEcho);

		getObj.put("iTotalRecords", this.count(privilege, page).getCount());
		getObj.put("iTotalDisplayRecords", this.count(privilege, page)
				.getCount());
		try {
			getObj.put("aaData",
					Json.ListChangeJson(page.getResult(), new Privilege()));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return getObj.toString();
	}

}
