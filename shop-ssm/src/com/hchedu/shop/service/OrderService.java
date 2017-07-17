package com.hchedu.shop.service;

import java.util.List;

import org.springframework.core.annotation.Order;

import com.hchedu.shop.entities.Cart;
import com.hchedu.shop.entities.Orders;
import com.hchedu.shop.entities.User;
import com.hchedu.shop.utils.PageBean;

public interface OrderService {

	List<Orders> findOrderByUid(Integer uid, int begin, int limit);

	PageBean<Orders> findPageByUid(Integer uid, Integer page);

	void addOrder(Orders orders);

	

	
	
}
