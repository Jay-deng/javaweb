package org.besteam.dao;

import org.besteam.domain.Dormitory;
/**
 * 宿舍
 * 查询所有(Base)
 * 添加（Base）
 * 修改（Base）
 * 删除（Base）
 * 条件查询
 * @author wff
 *
 */
public interface DormitoryDao extends BaseDao<Dormitory>{

	Dormitory findByDorName(String dorName);
	
}
