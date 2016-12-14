package com.sl.pms.dao;

import java.util.List;

import com.sl.pms.entity.Page;

public interface BaseDao <T>{
	
	public boolean insert(T t);
	
	public List<T> findAll(T t);
	
	public boolean delete(T t);
	
	public Page<T>  count(T t,Page<T> page);
	
	public boolean batchDelete(List<Integer> list,T t);

	public T findById(T t,int id);
	
	public boolean update(T t);
	
	public Page<T> findAllPage(T t,Page<T> page);
	
	
}
