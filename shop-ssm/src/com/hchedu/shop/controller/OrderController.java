package com.hchedu.shop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hchedu.shop.entities.Cart;
import com.hchedu.shop.entities.CartItems;
import com.hchedu.shop.entities.OrderItem;
import com.hchedu.shop.entities.Orders;
import com.hchedu.shop.entities.Product;
import com.hchedu.shop.entities.User;
import com.hchedu.shop.service.OrderItemService;
import com.hchedu.shop.service.OrderService;
import com.hchedu.shop.utils.PageBean;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderItemService orderItemService ;
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
	public String saveOrder(HttpSession session,Map<String,Object> map){
		User user=(User)session.getAttribute("existUser");
		if(user==null){
			return null;
		}
		Cart cart =(Cart)session.getAttribute("cart");
		if(cart==null){
			return "cart";
		}
		Orders order= new Orders();
		order.setOrdertime(new Date());
		order.setState(1);
		order.setUid(user.getUid());
		order.setTotal(cart.getTotal());
		order.setUser(user);
		order.setAddr(user.getAddr());
		order.setName(user.getName());
		order.setPhone(user.getPhone());
		int len=orderService.saveorders(order);
		List<OrderItem> orderItems =new ArrayList<>();
		List<CartItems> list=cart.getCartItems();
		for (CartItems cs : list) {
			Product product=cs.getProduct();
			Integer count=cs.getCount();
			Double subtotal=cs.getSubtotal();
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(count);
			orderItem.setPid(product.getPid());
			orderItem.setProduct(product);
			orderItem.setSubtotal(subtotal);
			orderItem.setOid(order.getOid());
			orderItemService.addorderItem(orderItem);		
			orderItems.add(orderItem);	
		}
		order.setOrderItems(orderItems);
		map.put("order", order);
		return "order";	
	}
		
}
