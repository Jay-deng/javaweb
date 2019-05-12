package org.besteam.dao;

import org.besteam.domain.User;
/**
 * 登录
 * 查询所有(base)
 * 添加(base)
 * 删除楼宇管理员
 * 条件查询
 * 查看学生缺勤记录
 * @author wff
 *
 */
public interface UserDao extends BaseDao<User>{
	//登录
	public User login(User user);
	//精准筛选学号
	public User findByUserCode(String userCode);
}
