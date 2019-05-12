package org.besteam.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 楼宇Bean
 * @author wff
 *
 */
public class Building {
	
	private Long building_id;	//楼宇id
	private String b_desc;	//楼宇介绍
	private String gender_in;	//可入住性别
	
	private Long user_id;	//管理员id
	private Set<Dormitory> dormitorys = new HashSet<Dormitory>();//宿舍

	public Long getBuilding_id() {
		return building_id;
	}

	public void setBuilding_id(Long building_id) {
		this.building_id = building_id;
	}

	public String getB_desc() {
		return b_desc;
	}

	public void setB_desc(String b_desc) {
		this.b_desc = b_desc;
	}

	public String getGender_in() {
		return gender_in;
	}

	public void setGender_in(String gender_in) {
		this.gender_in = gender_in;
	}


	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Set<Dormitory> getDormitorys() {
		return dormitorys;
	}

	public void setDormitorys(Set<Dormitory> dormitorys) {
		this.dormitorys = dormitorys;
	}

	@Override
	public String toString() {
		return "Building [building_id=" + building_id + ", b_desc=" + b_desc + ", gender_in=" + gender_in + ", user_id="
				+ user_id + "]";
	}
	
	
}
