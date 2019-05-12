package org.besteam.service;

import org.besteam.daoimpl.PageBean;
import org.besteam.domain.User;
/**
 * 用户业务层
 * @author wff
 *
 */
public interface UserService {
	//登录
	public User login(User user);
	//条件查询
	public PageBean<User> findByPage(User user,int pageCode);
	//精准筛选学号
	public User findByUserCode(String userCode);
	//添加用户
	public void addUser(User user);
	//删除管理员楼宇权限
	public void deleteAndChangeAdminInfo(Long user_id,Long building_id);
	//修改用户
	public void updateUser(User user);
	//通过id查学生
	public User findById(Long user_id);
	public void delete(User adminMsg);
	
}
