package com.sl.pms.test;

import com.sl.pms.dao.PrivilegeDao;
import com.sl.pms.dao.RoleDao;
import com.sl.pms.dao.UserDao;
import com.sl.pms.entity.Privilege;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring.xml","classpath*:hibernate.xml"})
public class roleTest {

	@Autowired
	private PrivilegeDao privilegeDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void PrivilegeTest(){
		System.out.print("我开始");
		Privilege p=new Privilege();
		p.setValue("添加工程");
		p.setName("update_project");
		Privilege p2=new Privilege();
		p2.setValue("删除工程");
		p2.setName("delete_project");
		Privilege p3=new Privilege();
		p3.setValue("修改工程");
		p3.setName("modify_project");
		privilegeDao.insert(p);
		privilegeDao.insert(p2);
		privilegeDao.insert(p3);
	}
}
