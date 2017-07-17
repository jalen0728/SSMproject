package com.hchedu.shop.service;

import com.hchedu.shop.entities.User;

public interface UserService {
	User seclectUserByUserName(String username);

	String checkUername(String username);

	void save(User user);

	User findUserByCode(String code);

	void modify(User user);
}
