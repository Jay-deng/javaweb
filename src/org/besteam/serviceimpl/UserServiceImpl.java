package org.besteam.serviceimpl;

import org.besteam.dao.UserDao;
import org.besteam.daoimpl.PageBean;
import org.besteam.domain.User;
import org.besteam.service.UserService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserServiceImpl implements UserService{
	//注入userDao
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public User login(User user) {
		return userDao.login(user);
	}
	@Override
	public PageBean<User> findByPage(User user,int pageCode) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("identity", user.getIdentity()));
		//筛选姓名
		if(user.getUsername()!=null&&!user.getUsername().trim().isEmpty()){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
		//筛选性别
		if(user.getGender()!=null&&!user.getGender().trim().isEmpty()){
			criteria.add(Restrictions.like("gender", "%"+user.getGender()+"%"));
		}
		//用户名、学号
		if(user.getUsercode()!=null&&!user.getUsercode().trim().isEmpty()){
			criteria.add(Restrictions.like("usercode", "%"+user.getUsercode()+"%"));
		}
		
		//根据身份继续筛选不同的选项
		if(user.getIdentity()==0){
			//0学生身份
			//仅筛选入住状态
			if(user.getCheck_in()!=null){
				criteria.add(Restrictions.eq("check_in", user.getCheck_in()));
			}
		}else if(user.getIdentity()==1){
			//1管理员
			//电话
			if(user.getPhone()!=null&&!user.getPhone().trim().isEmpty()){
				criteria.add(Restrictions.like("phone", "%"+user.getPhone()+"%"));
			}
			if(user.getBuilding_id()!=null){
				criteria.add(Restrictions.eq("building_id", user.getBuilding_id()));
			}
		}else{
			//系统管理员
			if(user.getIdentity()!=null){
				
			}
		}
		return userDao.findByPage(pageCode, 10, criteria);
	}
	
	@Override
	public User findByUserCode(String userCode) {
		return userDao.findByUserCode(userCode);
	}
	
	@Override
	public void addUser(User user) {
				userDao.save(user);
	}
	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}
	
	@Override
	public User findById(Long user_id) {
		return userDao.findById(user_id);
	}

	@Override
	public void deleteAndChangeAdminInfo(Long user_id,Long building_id) {
		User user = userDao.findById(user_id);
		user.setBuilding_id(building_id);
	}

	@Override
	public void delete(User adminMsg) {
		userDao.delete(adminMsg);
	}

}
