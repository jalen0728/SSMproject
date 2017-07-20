package com.hchedu.shop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hchedu.shop.dao.OrderItemMapper;
import com.hchedu.shop.entities.OrderItem;
import com.hchedu.shop.service.OrderItemService;
@Transactional
@Service
public class OrderItemImpl implements OrderItemService {
	@Autowired
	private OrderItemMapper oim;
	@Override
	public void addorderItem(OrderItem orderItem) {
		
		oim.insert(orderItem);
	}

}
