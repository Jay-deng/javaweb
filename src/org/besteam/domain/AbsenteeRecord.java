package org.besteam.domain;

import java.util.Date;

/**
 * 缺勤记录Bean
 * @author wff
 *
 */
public class AbsenteeRecord {
	private Long absentee_id;	//缺勤记录id
	private Long t_u_user;	//记录缺勤人id
	private Long buildingId;//楼宇id
	
	private Long dorId;//宿舍id
	private Date absentee_date;	//日期
	private String absentee_desc;//备注
	
	private User user;	//	缺勤人 
	

	public Long getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
	public Long getDorId() {
		return dorId;
	}
	public void setDorId(Long dorId) {
		this.dorId = dorId;
	}
	public Long getAbsentee_id() {
		return absentee_id;
	}
	public void setAbsentee_id(Long absentee_id) {
		this.absentee_id = absentee_id;
	}
	public Long getT_u_user() {
		return t_u_user;
	}
	public void setT_u_user(Long t_u_user) {
		this.t_u_user = t_u_user;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getAbsentee_date() {
		return absentee_date;
	}
	public void setAbsentee_date(Date absentee_date) {
		this.absentee_date = absentee_date;
	}
	public String getAbsentee_desc() {
		return absentee_desc;
	}
	public void setAbsentee_desc(String absentee_desc) {
		this.absentee_desc = absentee_desc;
	}
	@Override
	public String toString() {
		return "AbsenteeRecord [absentee_id=" + absentee_id + ", t_u_user=" + t_u_user + ", buildingId=" + buildingId
				+ ", dorId=" + dorId + ", absentee_date=" + absentee_date + ", absentee_desc=" + absentee_desc
				;
	}

	
	
}
