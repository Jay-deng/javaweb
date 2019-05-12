package org.besteam.web.action;

import org.apache.struts2.ServletActionContext;
import org.besteam.daoimpl.PageBean;
import org.besteam.domain.Building;
import org.besteam.domain.User;
import org.besteam.service.BuildingService;
import org.besteam.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
/**
 * 楼宇action
 * @author checkz520
 *
 */
public class BuildingAction extends ActionSupport implements ModelDriven<Building>{

	private static final long serialVersionUID = 4300789626596360064L;
	
	//注入模型驱动
	private Building building = new Building();
	public Building getModel() {
		return building;
	}
	
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	private BuildingService buildingService;
	public void setBuildingService(BuildingService buildingService) {
		this.buildingService = buildingService;
	}
	
	
	//注入pageCode
		private Integer pageCode;
		
		public void setPageCode(Integer pageCode) {
			
			this.pageCode = pageCode;
		}
		
	//查询所有楼宇信息	
	public String findByPageUI() {
		if(pageCode == null) {
			this.pageCode = 1;
		}
		
		PageBean<Building> bList = buildingService.findByPage(building, pageCode);
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("bList", bList);
		
		return "bListPage";
	}
	
	
	/**
	 * 修改楼宇信息
	 */
	
	public String updateBuildingUI() {
		//查询当前楼宇信息
		Building updateBuilding = buildingService.findBuildingInfo(building.getBuilding_id());
		//将当前楼宇信息放入值栈中
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("updateBuilding", updateBuilding);
		//转发到修改楼宇信息页面中
		return "updateBuilding";
	}
	
	/**
	 * 添加或修改当前的楼宇的管理员
	 */
	
	public String addBuildingAdminUI() {
		Building updateBudInfo = buildingService.findBuildingInfo(building.getBuilding_id());
		User u = new User();
		u.setBuilding_id(0L);
		u.setIdentity(1);
		PageBean<User> uPage = userService.findByPage(u, 1);
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("uPage", uPage);
		vs.set("bInfo", updateBudInfo);
		return "addBuildingAdmin";
		
	}
	
	
	//注入以前的管理员的id
	private Long oldId;
	public void setOldId(Long oldId) {
		this.oldId = oldId;
	}

	public String updateBInfo() {
		//将老管理员的楼宇信息置为0
		if(oldId!=null) {
			userService.deleteAndChangeAdminInfo(oldId, 0L);
		}
		
		//新楼宇和管理员信息
		User newUser = new User();
		newUser.setBuilding_id(building.getBuilding_id());
		newUser.setUser_id(building.getUser_id());
		buildingService.addAndChangeAdmingInfo(newUser);
		
		//louyu
		userService.deleteAndChangeAdminInfo(building.getUser_id(), building.getBuilding_id());
		
		
		ServletActionContext.getRequest().setAttribute("success", "操作成功!");
		
		
		return "pageui";
	}
	
	/**
	 * 跳转到添加楼宇界面
	 * @return
	 */
	public String addBuildingUI() {
		
		
		return "addBuilding";
	}
	
	/**
	 * 添加楼宇
	 */
	public String addBuilding() {
		buildingService.add(building);
		return "bListPage";
	}
}
