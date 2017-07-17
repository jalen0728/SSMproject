package com.hchedu.shop.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hchedu.shop.dao.OrderItemMapper;
import com.hchedu.shop.dao.OrdersMapper;
import com.hchedu.shop.dao.ProductMapper;
import com.hchedu.shop.entities.Cart;
import com.hchedu.shop.entities.OrderItem;
import com.hchedu.shop.entities.Orders;
import com.hchedu.shop.entities.Product;
import com.hchedu.shop.entities.User;
import com.hchedu.shop.service.OrderService;
import com.hchedu.shop.utils.PageBean;
@Transactional
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private Order order;
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public List<Orders> findOrderByUid(Integer uid, int begin, int limit) {
		List<Orders> order=ordersMapper.selectByUid(uid,begin,limit);
		for (Orders o : order) {
			Integer oid=o.getOid();
			List<OrderItem> orderItems=orderItemMapper.selectByOid(oid);
			o.setOrderItems(orderItems);
			for (OrderItem oI : orderItems) {
				Integer pid=oI.getPid();
				Product product=productMapper.selectByPrimaryKey(pid);
				oI.setProduct(product);
			}
		}
		return order;
	}
	@Override
	public PageBean<Orders> findPageByUid(Integer uid, Integer page) {
		PageBean<Orders> pageBean= new PageBean<>();
		pageBean.setPage(page);
		int limit=4;
		pageBean.setLimit(limit);
		int begin=(page-1)*limit;
		List<Orders> list=findOrderByUid(uid,begin,limit) ;
		pageBean.setList(list);
		int totalCount=ordersMapper.countByUid(uid);
		pageBean.setTotalCount(totalCount);
		int totalPage = 
		totalCount%limit == 0 ? totalCount/limit : totalCount/limit+1;
		pageBean.setTotalPage(totalPage);
		return pageBean;
		
	}
	@Override
	public void addOrder(Orders orders) {
		ordersMapper.insert(orders);
		
	}
	
	
}
