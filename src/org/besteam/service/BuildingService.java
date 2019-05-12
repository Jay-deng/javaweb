package org.besteam.service;

import java.util.List;

import org.besteam.daoimpl.PageBean;
import org.besteam.domain.Building;
import org.besteam.domain.User;
/**
 * 楼宇service
 * @author wff
 *
 */
public interface BuildingService {
	//条件查询楼宇
	public PageBean<Building> findByPage(Building building,int pageCode);
	//获取楼宇信息
	public Building findBuildingInfo(Long id);
	//清空楼宇管理员的信息
	public Building clearAdminInfo(Long building_id);
	//添加管理员信息
	public void addAndChangeAdmingInfo(User newUser);
	public void add(Building building);
	
	public List<Building> findByBuilding(String gender);
	public void update(Building bud);
}
