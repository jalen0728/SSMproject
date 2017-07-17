package com.hchedu.shop.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hchedu.shop.entities.Cart;
import com.hchedu.shop.entities.CartItems;
import com.hchedu.shop.entities.OrderItem;
import com.hchedu.shop.entities.Orders;
import com.hchedu.shop.entities.User;
import com.hchedu.shop.service.OrderService;
import com.hchedu.shop.utils.PageBean;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	//我的订单
	@RequestMapping("/order_findByUid")
	public String orderFindByUid(@RequestParam(value="page",required=false,defaultValue="1") Integer page,
			HttpSession session,Map<String ,Object> map){
		User user=(User)session.getAttribute("existUser");
		Integer uid=user.getUid();
		PageBean<Orders> pageBean=orderService.findPageByUid(uid, page);	
		map.put("pageBean", pageBean);
		return "orderList";
	}
	@RequestMapping("/order_saveOrder")
	public String saveOrder(Orders orders,List<OrderItem> ois,OrderItem oi,HttpSession session,Map<String,Object> map){
		User user=(User)session.getAttribute("existUser");
		Cart cart =(Cart)session.getAttribute("cart");
		List<CartItems> list=cart.getCartItems();
		orders.setTotal(cart.getTotal());
		orders.setUser(user);
		for (CartItems ci : list) {
			oi.setProduct(ci.getProduct());
			oi.setSubtotal(ci.getSubtotal());
			oi.setCount(ci.getCount());
			ois.add(oi);
		}
		orders.setAddr(user.getAddr());
		orders.setName(user.getName());
		orders.setPhone(user.getPhone());
		orders.setOrderItems(ois);
		orders.setState(1);
		orderService.addOrder(orders);
		return "order";
	}
}
