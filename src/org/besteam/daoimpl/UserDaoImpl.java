package org.besteam.daoimpl;

import java.util.List;

import org.besteam.dao.BuildingDao;
import org.besteam.dao.UserDao;
import org.besteam.domain.Building;
import org.besteam.domain.Dormitory;
import org.besteam.domain.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Override
	public User login(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("usercode", user.getUsercode()));
		criteria.add(Restrictions.eq("password", user.getPassword()));
		criteria.add(Restrictions.eq("state", 1));
		criteria.add(Restrictions.eq("identity", user.getIdentity()));
		List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list != null && list.size()>0){
			return list.get(0);			
		}
		return null;
	}
	

	@Override
	public User findByUserCode(String userCode) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("usercode", userCode));
		List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	
	
}
