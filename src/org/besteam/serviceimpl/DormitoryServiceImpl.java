package org.besteam.serviceimpl;

import org.besteam.dao.DormitoryDao;
import org.besteam.daoimpl.PageBean;
import org.besteam.domain.Dormitory;
import org.besteam.domain.User;
import org.besteam.service.DormitoryService;
import org.besteam.service.UserService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public class DormitoryServiceImpl implements DormitoryService{
	private DormitoryDao dormitoryDao;
	public void setDormitoryDao(DormitoryDao dormitoryDao) {
		this.dormitoryDao = dormitoryDao;
	}
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public PageBean<Dormitory> findByPage(Dormitory dormitory,int pageCode) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Dormitory.class);
		if(dormitory.getBuilding()!=null&&dormitory.getBuilding().getBuilding_id()!=null){
			criteria.add(Restrictions.eq("building", dormitory.getBuilding()));
		}
		if(dormitory.getDormitory_name()!=null&&!dormitory.getDormitory_name().trim().isEmpty()){
			criteria.add(Restrictions.like("dormitory_name", "%"+dormitory.getDormitory_name()+"%"));
		}
		if(dormitory.getDor_number()!=null){
			criteria.add(Restrictions.eq("dor_number", dormitory.getDor_number()));
		}
		if(dormitory.getDor_phone()!=null){
			criteria.add(Restrictions.like("dor_phone", "%"+dormitory.getDor_phone()+"%"));
		}
		return dormitoryDao.findByPage(pageCode, 10, criteria);
	}

	@Override
	public void changeStuDor(Long user_id,Long newDorId) {
		//学生原宿舍信息
		User user = userService.findById(user_id);
		//将原宿舍的人数-1
		Dormitory oldDormitory = dormitoryDao.findById(user.getDormitory().getDormitory_id());
		Integer oldDor_number = oldDormitory.getDor_number()-1;
		oldDormitory.setDor_number(oldDor_number);
		//新宿舍的人数+1
		Dormitory newDormitory = dormitoryDao.findById(newDorId);
		Integer newDor_number = newDormitory.getDor_number()+1;
		newDormitory.setDor_number(newDor_number);
		//把学生放到新宿舍
		user.setDormitory(newDormitory);
		user.setBuilding_id(newDormitory.getBuilding().getBuilding_id());
	}

	@Override
	public Dormitory getDorById(Long dormitory_id) {
		return dormitoryDao.findById(dormitory_id);
	}

	@Override
	public void save(Dormitory dormitory) {
		dormitoryDao.save(dormitory);
	}

	@Override
	public void addStuDor(Long DorId) {
		Dormitory dormitory = dormitoryDao.findById(DorId);
		Integer dor_number = dormitory.getDor_number()+1;
		dormitory.setDor_number(dor_number);;
		
	}

	@Override
	public Dormitory findByDorName(String dorName) {
		return dormitoryDao.findByDorName(dorName);
	}

	@Override
	public void update(Dormitory dormitory) {
		dormitoryDao.update(dormitory);
		
	}
}
