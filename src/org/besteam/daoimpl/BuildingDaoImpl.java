package org.besteam.daoimpl;

import java.util.List;

import org.besteam.dao.BuildingDao;
import org.besteam.domain.Building;
import org.besteam.domain.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class BuildingDaoImpl extends BaseDaoImpl<Building> implements BuildingDao{

	@Override
	public List<Building> findByBuilding(String gender) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Building.class);
		criteria.add(Restrictions.eq("gender_in", gender));
		List<Building> list = (List<Building>) this.getHibernateTemplate().findByCriteria(criteria);
			return list;
	}

	@Override
	public void deleteBuildingById(User user) {
		Building building=null;
		DetachedCriteria criteria = DetachedCriteria.forClass(Building.class);
		criteria.add(Restrictions.eq("user_id", user.getUser_id()));
		List<Building> list = (List<Building>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list!=null&&list.size()>0){
			building = list.get(0);
		}
		if(building!=null){
			building.setUser_id(null);
			update(building);
		}
	}
	
}
