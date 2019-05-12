package org.besteam.dao;

import java.util.List;

import org.besteam.daoimpl.PageBean;
import org.hibernate.criterion.DetachedCriteria;
/**
 * @author wff
 */
public interface BaseDao<T> {
	public void save(T t);
	
	public void delete(T t);
	
	public void update(T t);
	
	public T findById(Long id);
	
	public List<T> findAll();
	
	public PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
}
