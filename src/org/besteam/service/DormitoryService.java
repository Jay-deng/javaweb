package org.besteam.service;

import org.besteam.daoimpl.PageBean;
import org.besteam.domain.Dormitory;
import org.besteam.domain.User;
/**
 * 宿舍
 * 修改宿舍信息
 * @author wff 
 */
public interface DormitoryService {
	//条件查询
	public PageBean<Dormitory> findByPage(Dormitory dormitory,int pageCode);
	//修改
//	public Dormitory updateDor();
	//添加学生到宿舍(宿舍人数+1)
	public void addStuDor(Long DorId);
	//通过宿舍名称查找宿舍
	public Dormitory findByDorName(String dorName);
	//宿舍调换
	public void changeStuDor(Long user_id,Long newDorId);
	public Dormitory getDorById(Long dormitory_id);
	public void save(Dormitory dormitory);
	public void update(Dormitory dormitory);
}
