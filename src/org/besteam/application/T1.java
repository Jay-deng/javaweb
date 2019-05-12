package org.besteam.application;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.besteam.dao.DormitoryDao;
import org.besteam.dao.UserDao;
import org.besteam.domain.Dormitory;
import org.besteam.domain.User;
import org.besteam.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class T1{
	@Resource(name="userService")
	private UserService userService;
	@Test
	public void login(){
		userService.deleteAndChangeAdminInfo(6L, 0L);
	}
	@Test
	public void run1() {
		
	}
}
