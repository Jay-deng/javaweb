package org.besteam.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.besteam.daoimpl.PageBean;
import org.besteam.domain.AbsenteeRecord;
import org.besteam.domain.Dormitory;
import org.besteam.domain.User;
import org.besteam.service.AbsenteeRecordService;
import org.besteam.service.BuildingService;
import org.besteam.service.DormitoryService;
import org.besteam.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class AbsenteeRecordAction extends ActionSupport implements ModelDriven<AbsenteeRecord>{

	private static final long serialVersionUID = -5940812028614640249L;
	
	
	private AbsenteeRecord absenteeRecord = new AbsenteeRecord();
	@Override
	public AbsenteeRecord getModel() {
		// TODO Auto-generated method stub
		return absenteeRecord;
	}
	
	//注入service
	private AbsenteeRecordService absenteeRecordService;
	public void setAbsenteeRecordService(AbsenteeRecordService absenteeRecordService) {
		this.absenteeRecordService = absenteeRecordService;
	}
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private BuildingService buildingService;
	
	public void setBuildingService(BuildingService buildingService) {
		this.buildingService = buildingService;
	}
	
	private DormitoryService dormitoryService;

	public void setDormitoryService(DormitoryService dormitoryService) {
		this.dormitoryService = dormitoryService;
	}

	/**
	 * 缺勤记录登记界面
	 * @return
	 */
	public String recordUI() {
		//当前
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		
		return "record";
	}
	
	private Long building_id;
	public void setBuilding_id(Long building_id) {
		this.building_id = building_id;
	}
	private String userCode;
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * 缺勤登记
	 * @return
	 */
	public String record() {
		User user = userService.findByUserCode(userCode);
		if(user==null) {//没有此学生
			ServletActionContext.getRequest().setAttribute("isrecord", "学号输入有误!");
			return "record";
		}
		
		if(user.getIdentity()!=0) {//不是学生
			ServletActionContext.getRequest().setAttribute("isrecord", "学号输入有误!");
			return "record";
		}
		
		if(user.getCheck_in()!=1) {
			ServletActionContext.getRequest().setAttribute("isrecord", "该生未入住!");
			return "record";
		}
		
		if(user.getBuilding_id()!=building_id) {
			ServletActionContext.getRequest().setAttribute("isrecord", "请输入本栋楼宇学生学号!");
			return "record";
		}
		ServletActionContext.getRequest().setAttribute("recordUser", user);
		//学生所在楼宇
		return "addRecord";
	}
	
	public String addrec() throws ParseException {
		Long user_id = absenteeRecord.getUser().getUser_id();
		User findById = userService.findById(user_id);
		absenteeRecord.setUser(findById);
		Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date time = sdf.parse( nowTime );
        absenteeRecord.setAbsentee_date(time);
        absenteeRecordService.save(absenteeRecord);
		ServletActionContext.getRequest().setAttribute("isrecord", "登记成功!");
		return "record";
	}

	
	private Integer pageCode;
	public void setPageCode(Integer pageCode) {
		this.pageCode = pageCode;
	}
	
	private String dro_name;
	public void setDro_name(String dro_name) {
		this.dro_name = dro_name;
	}
	public String getDro_name() {
		return dro_name;
	}

	private String uc;
	public void setUc(String uc) {
		this.uc = uc;
	}

	public String findAbsUI() {
		if(pageCode == null) {
			pageCode = 1;
		}
		
		ServletActionContext.getRequest().setAttribute("uc", uc);
		PageBean<AbsenteeRecord> absPage = null;
		Long dormitory_id = null;
		if(dro_name!=null&&!dro_name.trim().equals("")) {
			//宿舍名称
			Dormitory dormitory  = 	dormitoryService.findByDorName(dro_name);
			ServletActionContext.getRequest().setAttribute("dro", dro_name);
			if(dormitory!=null) {
				dormitory_id = dormitory.getDormitory_id();
			}else {
				dormitory_id = 0L;
			}
		}
			User us = new User();
			us.setUser_id(0L);
			if(uc!=null&&!uc.trim().equals("")) {
				
				User findByUserCode = userService.findByUserCode(uc);
				if(findByUserCode==null) {
					findByUserCode = us;
				}
				absenteeRecord.setUser(findByUserCode);
			}
		
		
		User currUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(currUser.getIdentity()==2) {//系统管理员
			if(dormitory_id!=null) {
				absenteeRecord.setDorId(dormitory_id);
			}
			absPage = absenteeRecordService.findByPage(absenteeRecord, pageCode);
		}else {//楼宇管理员
			if(dormitory_id!=null) {
				absenteeRecord.setDorId(dormitory_id);
			}
			absenteeRecord.setBuildingId(currUser.getBuilding_id());
			absPage = absenteeRecordService.findByPage(absenteeRecord, pageCode);
		}
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("absPage", absPage);
		return "abs";
	}
	
	/**
	 * 查看个人缺勤记录
	 * @return
	 */
	
	public String showMe() {
		if(pageCode==null) {
			pageCode = 1;
		}
		User me = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		absenteeRecord.setUser(me);
		PageBean<AbsenteeRecord> absPage = absenteeRecordService.findByPage(absenteeRecord, pageCode);
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("absPage", absPage);
		return "showMe";
	}

}
