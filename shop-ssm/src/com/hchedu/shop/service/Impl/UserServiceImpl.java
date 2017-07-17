package com.hchedu.shop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hchedu.shop.dao.UserMapper;
import com.hchedu.shop.entities.User;
import com.hchedu.shop.service.UserService;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User seclectUserByUserName(String username) {
		User user=userMapper.selectUserByName(username);
		return user;
	}

	@Override
	public String checkUername(String username) {
		User user=userMapper.selectUserByName(username);
		if(user!=null){
			return "yes";
		}
		return "no";
	}

	@Override
	public void save(User user) {
		userMapper.insert(user);	
	}

	@Override
	public User findUserByCode(String code) {
		User user=userMapper.selectByCode(code);
		return user;
	}

	@Override
	public void modify(User user) {
		userMapper.updateByPrimaryKey(user);	
	}

}
