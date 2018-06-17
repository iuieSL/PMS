package com.sl.pms.dao.impl;

 

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sl.pms.dao.BaseDao;
import com.sl.pms.entity.Page;
import com.sl.pms.util.Json;

@Repository
public class BaseDaoImpl<T> implements BaseDao<T>{
	
	private static Logger logger = Logger.getLogger(BaseDaoImpl.class);  
 
	@Autowired
   private SessionFactory sessionFactory;
	
 	@Override
	public boolean insert(T t) {
 		Session session=null ;
 		try{
 			session=sessionFactory.openSession();
 			session.save(t);	
 		}catch(Exception e){
 			logger.debug(e.getMessage());
 			return false;	
 		}finally{
 			session.close();
 		}
 		return true;
	}

	@Override
	public List<T> findAll(T t) {
		Class<? extends Object> c = t.getClass();
		String name = c.getSimpleName();
		String hql = "from " + name;

		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		List<T> result = query.list();
		session.close();
		return result;

	}

	@Override
	public boolean delete(T t) {
		Session session=null;
		try{
			session=sessionFactory.openSession();
			session.delete(t);
		}catch(Exception e){
			logger.debug(e.getMessage());
 			return false;	
		}finally{
			session.flush();
			session.close();
		}
		return true;
	}

	@Override
	public Page<T>  count(T t,Page<T> page) {
		 boolean hasif=false;
		Class<? extends Object> c = t.getClass();
		StringBuilder hql = new StringBuilder("select count(*) from " + c.getSimpleName()
				+ " where 1=1");
		Field[] fields = c.getDeclaredFields();	
		//参数列表
		Map<String, Object> paramter = new LinkedHashMap<String, Object>();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			try {
					//特殊的情况
					// 跳过静态属性
					String mod = Modifier.toString(fields[i].getModifiers());
					if (mod.indexOf("static") != -1)
						continue;
					// 获得类型
					String className = fields[i].getType().getSimpleName();
					// 忽略Set类型
					if (className.equalsIgnoreCase("Set")) {
						continue;
					}
				
				//对id的判断
				if(fields[i].getName().equalsIgnoreCase("id")&&fields[i].getInt(t)==0) continue; 
				
				if (fields[i].get(t) != null&&!hasif) {
					hql.append(" and " + fields[i].getName() + " like :"+fields[i].getName());
					Method method1 = c.getMethod("get" + Json.toUpperCase(fields[i].getName()));
					paramter.put(fields[i].getName(), method1.invoke(t));
					hasif=true;
				}else if(hasif&&fields[i].get(t) != null){
					hql.append(" or " + fields[i].getName() + " like :"+fields[i].getName());
					Method method1 = c.getMethod("get" + Json.toUpperCase(fields[i].getName()));
					paramter.put(fields[i].getName(), method1.invoke(t));
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		//开始查询
		Query query = sessionFactory.openSession().createQuery(hql.toString());
		 Iterator<Map.Entry<String, Object>> it = paramter.entrySet().iterator();
		 while(it.hasNext()){
			 Map.Entry<String, Object> entry=it.next(); 
			 query.setParameter(entry.getKey(), "%"+entry.getValue()+"%");
		 }
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		int count=list!=null?new Integer(list.get(0).toString()):0;
		page.setCount(count);
		return page; 
	}

    /**
     * 批量删除(hql拼接)
     */
	@Override
	public boolean batchDelete(List<Integer> list,T t) {
		Session session=null;
		try{
			session = sessionFactory.openSession();
			StringBuilder hql=new StringBuilder( "delete from "+t.getClass().getSimpleName()+" where id in (:ids)");
			Query q= session.createQuery(hql.toString()).setParameterList("ids", list);
		    q.executeUpdate();
		}catch(Exception e){
			logger.debug(e.getMessage());
 			return false;	
		}finally{
			session.close();
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public T findById(T t,int id){
		Session session=sessionFactory.openSession();
	   Object object= session.get(t.getClass(), id);
	    if(object!=null&&!object.equals("")){
	    	session.close();
	    	return (T)object;
	    }
	    session.close();
		return null;
	}

	@Override
	public boolean update(T t) {
		Session session=null;
		try{
			 session=sessionFactory.openSession();	
			 session.saveOrUpdate(t);
		}catch(Exception e){
			logger.debug(e.getMessage());
 			return false;	
		}finally{
			session.flush();
			session.close();
		}
      return true;
	
	}

	@Override
	public Page<T> findAllPage(T t, Page<T> page) {
		 boolean hasif=false;
		
		Class<? extends Object> c = t.getClass();
		//有or必须扩起来
		StringBuilder hql = new StringBuilder("from " + c.getSimpleName()
				+ " where 1=1");
		Field[] fields = c.getDeclaredFields();
		//参数列表
		Map<String, Object> paramter = new LinkedHashMap<String, Object>();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			try {
				//特殊的情况
				// 跳过静态属性
				String mod = Modifier.toString(fields[i].getModifiers());
				if (mod.indexOf("static") != -1)
					continue;
				// 获得类型
				String className = fields[i].getType().getSimpleName();
				// 忽略Set类型
				if (className.equalsIgnoreCase("Set")) {
					continue;
				}
				//对id的判断
				if(fields[i].getName().equalsIgnoreCase("id")&&fields[i].getInt(t)==0) continue; 
				if (fields[i].get(t) != null&&!hasif) {
					hql.append(" and " + fields[i].getName() + " like :"+fields[i].getName());
					Method method1 = c.getMethod("get" + Json.toUpperCase(fields[i].getName()));
					paramter.put(fields[i].getName(), method1.invoke(t));
					hasif=true;
				}else if(hasif&&fields[i].get(t) != null){
					hql.append(" or " + fields[i].getName() + " like :"+fields[i].getName());
					Method method1 = c.getMethod("get" + Json.toUpperCase(fields[i].getName()));
					paramter.put(fields[i].getName(), method1.invoke(t));
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		System.out.println(hql.toString());
		//开始查询
		Query query = sessionFactory.openSession().createQuery(hql.toString());
		 Iterator<Map.Entry<String, Object>> it = paramter.entrySet().iterator();
		 while(it.hasNext()){
			 Map.Entry<String, Object> entry=it.next(); 
			 //模糊查询
			 query.setParameter(entry.getKey(),  "%"+entry.getValue()+ "%");
		 }
		  if(page!=null){
			 query.setFirstResult(page.getNextData());
			 query.setMaxResults(page.getPageSize());
		 } 
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		page.setResult(list);
		return page;
	}

}
