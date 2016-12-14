package com.sl.pms.dao.impl;

 

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sl.pms.dao.PrivilegeDao;
import com.sl.pms.entity.Privilege;
@Repository
public class PrivilegeDaoImpl extends BaseDaoImpl<Privilege> implements PrivilegeDao{

	@Autowired
	   private SessionFactory sessionFactory;
	
	@Override
	public  List<Privilege> findbyObject(Privilege p) {
		Session session=sessionFactory.openSession();
	    String hql="from Privilege where name=:name and value=:value";
	    Query query=session.createQuery(hql);
	    query.setString("name", p.getName());
	    query.setString("value", p.getValue());
	    List<Privilege>  result=query.list();
		return result;
	}

	@Override
	public List<Privilege> findByName(String name) {
		Session session=sessionFactory.openSession();
		String hql="from Privilege where name like :name";
		Query query=session.createQuery(hql);
		query.setString("name", "%"+name+"%");   
		List<Privilege> result=query.list();
		return result;
	}

}
