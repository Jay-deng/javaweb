package org.besteam.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户Bean
 * @author wff
 *
 */
public class User {
	private Long user_id; //用户id
	private String username; //姓名
	private String usercode; //账号、用户名、学号
	
	private String password; //密码
	private String gender; //姓名
	private String phone; //电话
	
	private Integer identity; //身份
	private Integer check_in; //入住
	private Integer state; //1能登录 0不能登录
	
	private Long building_id; //楼宇id
	private Dormitory dormitory; //宿舍id
	private Set<AbsenteeRecord> absenteeRecords = new HashSet<>();//缺勤记录
	

	public Long getUser_id() {
		return user_id;
	}


	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getUsercode() {
		return usercode;
	}


	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Integer getIdentity() {
		return identity;
	}


	public void setIdentity(Integer identity) {
		this.identity = identity;
	}


	public Integer getCheck_in() {
		return check_in;
	}


	public void setCheck_in(Integer check_in) {
		this.check_in = check_in;
	}


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}


	public Long getBuilding_id() {
		return building_id;
	}


	public void setBuilding_id(Long building_id) {
		this.building_id = building_id;
	}


	public Dormitory getDormitory() {
		return dormitory;
	}


	public void setDormitory(Dormitory dormitory) {
		this.dormitory = dormitory;
	}


	public Set<AbsenteeRecord> getAbsenteeRecords() {
		return absenteeRecords;
	}


	public void setAbsenteeRecords(Set<AbsenteeRecord> absenteeRecords) {
		this.absenteeRecords = absenteeRecords;
	}


	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", usercode=" + usercode + ", password="
				+ password + ", gender=" + gender + ", phone=" + phone + ", identity=" + identity + ", check_in="
				+ check_in + ", state=" + state + ", building_id=" + building_id + "]";
	}
	
	
}
