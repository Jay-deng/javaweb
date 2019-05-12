package org.besteam.daoimpl;

import java.util.List;

import org.besteam.dao.DormitoryDao;
import org.besteam.domain.Dormitory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class DormitoryDaoImpl extends BaseDaoImpl<Dormitory> implements DormitoryDao{

	@Override
	public Dormitory findByDorName(String dorName) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Dormitory.class);
		if(dorName!=null&&!dorName.trim().isEmpty()){
			criteria.add(Restrictions.eq("dormitory_name", dorName));
		}
		List<Dormitory> list = (List<Dormitory>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	
}
