package org.besteam.service;

import org.besteam.daoimpl.PageBean;
import org.besteam.domain.AbsenteeRecord;

public interface AbsenteeRecordService {
	//查看学生缺勤记录
	public PageBean<AbsenteeRecord> findByPage(AbsenteeRecord absenteeRecord,int pageCode);

	public void save(AbsenteeRecord absenteeRecord);
}
