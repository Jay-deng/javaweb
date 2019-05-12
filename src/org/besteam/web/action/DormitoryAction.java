package org.besteam.web.action;

import org.besteam.daoimpl.PageBean;
import org.besteam.domain.Building;
import org.besteam.domain.Dormitory;
import org.besteam.service.BuildingService;
import org.besteam.service.DormitoryService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 宿舍action
 * 
 * @author checkz520
 *
 */
public class DormitoryAction extends ActionSupport implements ModelDriven<Dormitory> {

	private static final long serialVersionUID = 9205366981981150538L;

	private Dormitory dormitory = new Dormitory();

	public Dormitory getModel() {
		if(dormitory==null) {
			dormitory = new Dormitory();
		}
		return dormitory;
	}

	private DormitoryService dormitoryService;

	public void setDormitoryService(DormitoryService dormitoryService) {
		this.dormitoryService = dormitoryService;
	}
	
	private BuildingService buildingService;

	public void setBuildingService(BuildingService buildingService) {
		this.buildingService = buildingService;
	}

	// 注入pageCode	
	private Integer pageCode;

	public void setPageCode(Integer pageCode) {
		
		this.pageCode = pageCode;
	}
	
	/**
	 * 查询所有宿舍
	 * @return
	 */
	public String findByPageUI() {
		if (pageCode == null) {
			this.pageCode = 1;
		}
		PageBean<Dormitory> bList = dormitoryService.findByPage(dormitory, pageCode);
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("dList", bList);
		
		return "dListPage";
	}
	
	/**
	 * 跳转到查看宿舍信息界面
	 */
	public String dorInfoUI() {
		
		Dormitory dor = dormitoryService.getDorById(dormitory.getDormitory_id());
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("dor", dor);
		return "dorInfo";
	}
	
	/**
	 * 跳转到添加宿舍界面
	 */
	public String addDorUI() {
		
		Building building = new Building();
		building.setGender_in(dormitory.getGender_in());
		//可入住楼宇
		PageBean<Building> bud = buildingService.findByPage(building, 1);
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("bud", bud);
		return "addDor";
		
	}
	
	public String addDor() {
		Building bud = buildingService.findBuildingInfo(dormitory.getBuilding().getBuilding_id());
		dormitory.setBuilding(bud);
		//保存宿舍信息
		dormitoryService.save(dormitory);
		
		return "dListPage";
	}
}
