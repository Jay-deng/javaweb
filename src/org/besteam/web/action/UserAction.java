package org.besteam.web.action;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.besteam.daoimpl.PageBean;
import org.besteam.domain.Building;
import org.besteam.domain.Dormitory;
import org.besteam.domain.User;
import org.besteam.service.BuildingService;
import org.besteam.service.DormitoryService;
import org.besteam.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
/**
 * 用户action
 * @author checkz520
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {

	private static final long serialVersionUID = -3174298997155907493L;
	private User user = new User();

	@Override
	public User getModel() {
		return user;
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
	 * 登录
	 * 
	 * @return
	 */

	public String login() {

		User loginUser = userService.login(user);
		if (loginUser != null) {// 若用户不为空则登录成功
			// 将用户存入session中
			ServletActionContext.getRequest().getSession().setAttribute("existUser", loginUser);
			// 移除登录错误提示
			ServletActionContext.getRequest().removeAttribute("fail");
			return SUCCESS;

		} else {// 密码错误重新登录
			ServletActionContext.getRequest().setAttribute("fail", "账户密码错误!");
			ServletActionContext.getRequest().setAttribute("failUser", user);
			ServletActionContext.getRequest().getSession().removeAttribute("existUser");
			
			/*Cookie cookie = new Cookie("failUser", user.getUsercode());
			cookie.setMaxAge(3600);
			cookie.setPath(ServletActionContext.getRequest().getContextPath()+"/");
			ServletActionContext.getResponse().addCookie(cookie);
			System.out.println(cookie.getValue());*/
			return LOGIN;
		}
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */

	public String logout() {
		User exitUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if (exitUser != null) {// 若当前已经登录
			// 移除已经登录的用户 进入登录界面
			ServletActionContext.getRequest().getSession().removeAttribute("existUser");
			ServletActionContext.getRequest().setAttribute("fail", "请重新登录！");
			return LOGIN;
		}

		return LOGIN;
	}

	/**
	 * 跳转到主页面
	 */
	public String indexUI() {
		return SUCCESS;
	}

	/**
	 * 跳转到修改密码界面
	 * 
	 * @return
	 */
	public String changePwdUI() {
		return "changePwd";

	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String changePwd() {
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		existUser.setPassword(user.getPassword());
		userService.updateUser(existUser);
		ServletActionContext.getRequest().getSession().removeAttribute("existUser");
		ServletActionContext.getRequest().setAttribute("fail", "请用新密码登录!");
		// 返回到登录
		return LOGIN;

	}

	
	
	//注入pageCode
	private Integer pageCode;
	
	public void setPageCode(Integer pageCode) {
		
		this.pageCode = pageCode;
	}

	/**
	 * 通过条件查询用户
	 */
	public String findByPageUI() {
		if(pageCode == null) {
			this.pageCode = 1;
		}
		//分页查询   根据model中的身份信息来查询
		PageBean<User> pageBean = userService.findByPage(user, pageCode);	
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("pageBean", pageBean);
		//若身份为0 则跳转到students界面
		if (user.getIdentity() == 0) {
			return "students";
		}
		//否则返回到管理员管理界面
		return "admins";

	}
	//添加用户
	public String addUserUI() {
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("aUser", user);
		return "addUser";
		
	}
	
	/**
	 * 添加用户
	 * @return
	 */
	public String addUser() {
		
		userService.addUser(user);
		
		return SUCCESS;
		
	}
	
	/**
	 * 检查用户名是否使用
	 * @throws IOException 
	 */
	

	public String checkUsercode() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("txt/html;chartset=utf-8");
		if(user.getUsercode().length()==0){
			//户名为空
			response.getWriter().println("2");
			return NONE;
		}
		
		User u = userService.findByUserCode(user.getUsercode());
		if(u==null) {
			//当前用户名可用
			response.getWriter().println("1");
			return NONE;
		}else {
			//该用户名已存在
			response.getWriter().println("0");
			return NONE;
		}
		
	}
	
	/**
	 * updateUserUI更改用户信息ui界面跳转
	 */
	
	public String updateUserUI() {
		//通过id获取需要修改的用户的信息
		User updateUser = userService.findById(user.getUser_id());
		//存入到值栈到页面读取
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("updateUser", updateUser);
		
		//跳转到修该用户界面
		return "updateUser";
		
	}
	
	/**
	 * 学生入住
	 */
	public String studnetinUI() {
		
		return "studntin";
		
	}
	
	public String studentin() {
		//查询学生信息
		User in = userService.findByUserCode(user.getUsercode());
		if(in==null) {//如果没有该生 则返回输入界面
			ServletActionContext.getRequest().setAttribute("check_in", "没有此学生!");
			return "studntin";
		}
		
		if(in.getIdentity()!=0) {//判断是否为学生
			ServletActionContext.getRequest().setAttribute("check_in", "没有此学生!");
			return "studntin";
		}
		
		if(in.getCheck_in()==1||in.getCheck_in()==2) {
			ServletActionContext.getRequest().setAttribute("check_in", "该生已入住或已迁出!");
			//已入住  或已迁出
			return "studntin";
		}
		Long user_id = in.getUser_id();
		//不为空则查询该生性别  查询可入住楼宇
		
		//可入住楼宇
		List<Building> bList =  buildingService.findByBuilding(in.getGender());
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("inbud", bList);
		vs.set("user_id", user_id);
		return "selectDro";
		
	}
	
	public String selectDro2() {
		Long user_id = user.getUser_id();
		User uu = userService.findById(user_id);
		if(uu.getCheck_in()==0) {//如果该生未入住过
			Long building_id = user.getBuilding_id();
			Building findBuildingInfo = buildingService.findBuildingInfo(building_id);
			//可用宿舍信息
			Set<Dormitory> dormitorys = findBuildingInfo.getDormitorys();
			ValueStack vs = ActionContext.getContext().getValueStack();
			vs.set("dormitorys", dormitorys);
			vs.set("user_id", user_id);
			vs.set("building_id", building_id);
			return "selectDro2";
		}else {//如果该生已经入住
			Long dormitory_id = uu.getDormitory().getDormitory_id();
			Long building_id = user.getBuilding_id();
			Building findBuildingInfo = buildingService.findBuildingInfo(building_id);
			//可用宿舍信息
			Set<Dormitory> dormitorys = findBuildingInfo.getDormitorys();
			ValueStack vs = ActionContext.getContext().getValueStack();
			vs.set("dormitorys", dormitorys);
			vs.set("user_id", user_id);
			vs.set("building_id", building_id);
			vs.set("dormitory_id", dormitory_id);
			
			return "changeDro2";
		}
		
		
		
	}
	
	
	/**
	 * 学生入住
	 * @return
	 */
	public String checkin() {
		Long building_id = user.getBuilding_id();//要入住的楼宇
		System.out.println(building_id);
		System.out.println("///////////////////////");
		Long dormitory_id = user.getDormitory().getDormitory_id();//要入住的宿舍
		System.out.println(dormitory_id);
		System.out.println("///////////////////////");
		User u = userService.findById(user.getUser_id());
		Dormitory dorById = dormitoryService.getDorById(dormitory_id);
		dorById.setDor_number(dorById.getDor_number()+1);
		u.setCheck_in(1);
		u.setDormitory(dorById);
		u.setBuilding_id(building_id);
		userService.updateUser(u);
		
		ServletActionContext.getRequest().setAttribute("check_in", "成功入住!");
		return "studntin";
	}
	
	
	
	
	/**
	 * 学生迁出
	 */
	
	public String outSchoolUI() {
		
		
		return "outSchool";
	}
	
	
	public String outSchoolCheck() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("txt/html;chartset=utf-8");
		if(user.getUsercode().length()==0){
			//户名为空
			response.getWriter().println("2");
			return NONE;
		}
		
		User u = userService.findByUserCode(user.getUsercode());
		if(u==null) {
			//无此用户
			response.getWriter().println("1");
			return NONE;
		}else {
			
			if(u.getIdentity()!=0) {//不是学生
				response.getWriter().println("1");
				return NONE;
			}
			
			if(u.getCheck_in()!=1) {
				response.getWriter().println("3");
				return NONE;
			}
			//该用户名已存在
			response.getWriter().println("0");
			return NONE;
		}
		
	}
	
	/**
	 * 迁出
	 */
	public String outSchool() {
		//获取用户账户
		String usercode = user.getUsercode();
		//获取用户信息
		User u = userService.findByUserCode(usercode);
		Dormitory dormitory = u.getDormitory();
		//将宿舍人数减一
		dormitory.setDor_number(dormitory.getDor_number()-1);
		//设置入住状态为迁出
		u.setCheck_in(2);
		u.setState(0);
		//设置用户的楼宇为0
		u.setBuilding_id(0L);
		dormitoryService.update(dormitory);
		u.setDormitory(null);
		userService.updateUser(u);
		ServletActionContext.getRequest().setAttribute("outSucc", "迁出成功!");
		
		return "outSchool";
	}
	
	
	/**
	 * 学生宿舍调换
	 */
	
	public String changeDroUI() {
		
		
		return "changeDro";
	}
	
	
	/**
	 * 学生寝室调换
	 * @return
	 */
	public String changeDro() {
		//查询学生信息
		User in = userService.findByUserCode(user.getUsercode());
		if(in==null) {//如果没有该生 则返回输入界面
			ServletActionContext.getRequest().setAttribute("check_in", "没有此学生!");
			return "changeDro";
		}
		
		if(in.getIdentity()!=0) {//判断是否为学生
			ServletActionContext.getRequest().setAttribute("check_in", "没有此学生!");
			return "changeDro";
		}
		
		if(in.getCheck_in()==0||in.getCheck_in()==2) {
			ServletActionContext.getRequest().setAttribute("check_in", "该生尚未入住!");
			//未入住
			return "changeDro";
		}
		
		
		Long user_id = in.getUser_id();
		
		//不为空则查询该生性别  查询可入住楼宇
		
		//可入住楼宇
		List<Building> bList =  buildingService.findByBuilding(in.getGender());
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("inbud", bList);
		vs.set("user_id", user_id);
		return "selectDro";
		
	}
	
/**
 * 更换宿舍最后一步
 * @return
 */
	public String changeDroLast() {
		dormitoryService.changeStuDor(user.getUser_id(), user.getDormitory().getDormitory_id());
		ServletActionContext.getRequest().setAttribute("check_in", "宿舍调换成功!");
		
		return "changeDro";
	}
	
	public String deleteAdmin() {
		//查询管理员信息
		Long admin_id = user.getUser_id();
		System.out.println("................."+admin_id);
		User adminMsg = userService.findById(admin_id);
		//获取管理员的楼宇id
		Long building_id = adminMsg.getBuilding_id();
		if(building_id==0) {//为0 则证明未管理楼宇
			userService.delete(adminMsg);
		}else {//有管理的楼宇
			Building bud = buildingService.findBuildingInfo(building_id);
			bud.setUser_id(0L);//将楼宇的管理员置空
			buildingService.update(bud);
			userService.delete(adminMsg);
		}
		return SUCCESS;
		
	}
	
}
