package org.besteam.serviceimpl;

import java.util.List;

import org.besteam.dao.BuildingDao;
import org.besteam.daoimpl.PageBean;
import org.besteam.domain.Building;
import org.besteam.domain.User;
import org.besteam.service.BuildingService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public class BuildingServiceImpl implements BuildingService{
	
	private BuildingDao buildingDao;
	public void setBuildingDao(BuildingDao buildingDao) {
		this.buildingDao = buildingDao;
	}
	
	@Override
	public PageBean<Building> findByPage(Building building,int pageCode) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Building.class);
		if(building.getB_desc()!=null&&!building.getB_desc().trim().equals("")){
			criteria.add(Restrictions.eq("b_desc", building.getB_desc()));
		}
		if(building.getGender_in()!=null&&!building.getGender_in().trim().equals("")){
			criteria.add(Restrictions.eq("gender_in", building.getGender_in()));
		}
		if(building.getUser_id()!=null){
			criteria.add(Restrictions.eq("user_id", building.getUser_id()));			
		}
		return buildingDao.findByPage(pageCode, 10, criteria);
	}

	@Override
	public Building findBuildingInfo(Long id) {
		return buildingDao.findById(id);
	}

	@Override
	public Building clearAdminInfo(Long building_id) {
		//获取楼宇信息
		Building building = buildingDao.findById(building_id);
		//将楼宇中的管理员信息清空
		building.setUser_id(0L);
		return building;
	}

	@Override
	public void addAndChangeAdmingInfo(User newUser) {
		Building building = buildingDao.findById(newUser.getBuilding_id());
		building.setUser_id(newUser.getUser_id());
	}

	@Override
	public void add(Building building) {
		buildingDao.save(building);
	}

	@Override
	public List<Building> findByBuilding(String gender) {
		return	buildingDao.findByBuilding(gender);
		
	}

	@Override
	public void update(Building bud) {
		buildingDao.update(bud);
	}

	
}
