package org.besteam.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 宿舍Bean
 * @author wff
 */
public class Dormitory {
	private Long dormitory_id;	//宿舍id
	private String dor_phone;	//电话
	private Integer dor_Mnumber;	//最大人数
	
	private Integer dor_number;	//人数
	private	String gender_in;	//入住性别
	private String dormitory_name;	//宿舍编码
	
	private Set<User> users = new HashSet<>();	//所住人员
	private Building building;	//楼宇id

	public Long getDormitory_id() {
		return dormitory_id;
	}

	public void setDormitory_id(Long dormitory_id) {
		this.dormitory_id = dormitory_id;
	}

	public String getDor_phone() {
		return dor_phone;
	}

	public void setDor_phone(String dor_phone) {
		this.dor_phone = dor_phone;
	}

	public Integer getDor_Mnumber() {
		return dor_Mnumber;
	}

	public void setDor_Mnumber(Integer dor_Mnumber) {
		this.dor_Mnumber = dor_Mnumber;
	}

	public Integer getDor_number() {
		return dor_number;
	}

	public void setDor_number(Integer dor_number) {
		this.dor_number = dor_number;
	}

	public String getGender_in() {
		return gender_in;
	}

	public void setGender_in(String gender_in) {
		this.gender_in = gender_in;
	}


	public String getDormitory_name() {
		return dormitory_name;
	}

	public void setDormitory_name(String dormitory_name) {
		this.dormitory_name = dormitory_name;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Dormitory [dormitory_id=" + dormitory_id + ", dor_phone=" + dor_phone + ", dor_Mnumber=" + dor_Mnumber
				+ ", dor_number=" + dor_number + ", gender_in=" + gender_in + ", dormitory_name=" + dormitory_name
				+ "]";
	}
	
}
