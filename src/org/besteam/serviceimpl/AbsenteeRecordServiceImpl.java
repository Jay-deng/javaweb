package org.besteam.serviceimpl;

import org.besteam.dao.AbsenteeRecordDao;
import org.besteam.daoimpl.PageBean;
import org.besteam.domain.AbsenteeRecord;
import org.besteam.service.AbsenteeRecordService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public class AbsenteeRecordServiceImpl implements AbsenteeRecordService{
	private AbsenteeRecordDao absenteeRecordDao;
	public void setAbsenteeRecordDao(AbsenteeRecordDao absenteeRecordDao) {
		this.absenteeRecordDao = absenteeRecordDao;
	}
 
	@Override
	public PageBean<AbsenteeRecord> findByPage(AbsenteeRecord absenteeRecord,int pageCode) {
		DetachedCriteria criteria = DetachedCriteria.forClass(AbsenteeRecord.class);
		if(absenteeRecord.getUser()!=null){
			criteria.add(Restrictions.eq("user",absenteeRecord.getUser()));
		}
		if(absenteeRecord.getBuildingId()!=null){
			criteria.add(Restrictions.eq("buildingId",absenteeRecord.getBuildingId()));
		}
		if(absenteeRecord.getDorId()!=null){
			criteria.add(Restrictions.eq("dorId",absenteeRecord.getDorId()));
		}
		return absenteeRecordDao.findByPage(pageCode, 10, criteria);
	}

	@Override
	public void save(AbsenteeRecord absenteeRecord) {
		absenteeRecordDao.save(absenteeRecord);
		
	}
}
