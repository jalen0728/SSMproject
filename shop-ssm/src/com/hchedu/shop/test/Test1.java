package com.hchedu.shop.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hchedu.shop.dao.UserMapper;
import com.hchedu.shop.entities.User;

public class Test1 {
	@Test
	public void test(){
	ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring-mybatis.xml");
	UserMapper um =  (UserMapper) ac.getBean("userMapper");
	List<User> list = um.selectByExample(null);
	System.out.println(list);
}
}