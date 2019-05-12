package org.besteam.daoimpl;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.besteam.dao.BaseDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;


@SuppressWarnings("all")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class clazz;
	public BaseDaoImpl(){
		Class c = this.getClass();
		Type type = c.getGenericSuperclass();
		
		if(type instanceof ParameterizedType){
			ParameterizedType ptype = (ParameterizedType) type;
			
			Type[] types = ptype.getActualTypeArguments();
			this.clazz = (Class) types[0];
		}
	}
	
	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public T findById(Long id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
	}

	@Override
	public PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		PageBean<T> page = new PageBean<>();
				page.setPageCode(pageCode);
				page.setPageSize(pageSize);
				
				criteria.setProjection(Projections.rowCount());
				List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
				if(list != null && list.size() > 0){
					int totalCount = list.get(0).intValue();
					page.setTotalCount(totalCount);
				}
				
				criteria.setProjection(null);
				
				List<T> beanList = (List<T>) this.getHibernateTemplate().findByCriteria(criteria, (pageCode-1)*pageSize, pageSize);
				page.setBeanList(beanList);
				return page;
	}

}
