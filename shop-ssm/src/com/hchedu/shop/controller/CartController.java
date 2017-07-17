package com.hchedu.shop.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hchedu.shop.entities.Cart;
import com.hchedu.shop.entities.CartItems;
import com.hchedu.shop.entities.Product;
import com.hchedu.shop.entities.User;
import com.hchedu.shop.service.ProductService;

@Controller
public class CartController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/cart_myCart")
	public String myCart(HttpSession session,Map<String,Object> map){
		User user =(User) session.getAttribute("existUser");
		if(user==null){
			map.put("cartMsg", "亲,你还没有登录,请先登录");
			return "cart";
		} 
		Cart cart =(Cart) session .getAttribute("cart");
		if(cart==null){
			cart= new Cart();
			session.setAttribute("cart", cart);
		}
		if(cart.getCartItems()==null||cart.getCartItems().size()==0){
			map.put("cartMsg", "亲,你的购物车是空的,请先购物");
			return "cart";
		}
		map.put("cart", cart);
		return "cart";
	}
	
	@RequestMapping("/cart_addCart")
	public String addCart(@RequestParam("pid")Integer pid,
			@RequestParam("count")Integer count,Map<String,Object> map,HttpSession session){
		User user =(User) session.getAttribute("existUser");
		if(user==null){
			map.put("cartMsg", "亲,你还没有登录,请先登录");
			return "cart";
		} 
		Cart cart= (Cart) session.getAttribute("cart");
		if(cart==null){
			cart= new Cart();
			session.setAttribute("cart", cart);
		}
		Product product=productService.findProductByPid(pid);
		CartItems cartItems= new CartItems();
		cartItems.setProduct(product);
		cartItems.setCount(count);		
		cart.addCartItems(cartItems);
		if(cart.getTotal()==null){
			cart.setTotal(cartItems.getSubtotal());
		}else{
			cart.setTotal(cart.getTotal()+cartItems.getSubtotal());
		}	
		map.put("cart", cart);
		return "cart";
	}
	@RequestMapping("/cart_removeCart")
	public String removeCart(@RequestParam("pid")Integer pid,HttpSession session){
		Cart cart=(Cart) session.getAttribute("cart");
		Map<Integer, CartItems> carts=cart.getCarts();
		double subToyal=carts.get(pid).getSubtotal();
		carts.remove(pid);
		
		cart.setTotal(cart.getTotal()-subToyal);
		return "cart";
	}
	@RequestMapping("/cart_clearCart")
	public String removeAllCart(HttpSession session){
		Cart cart=(Cart) session.getAttribute("cart");
		Map<Integer, CartItems> carts=cart.getCarts();
		carts.clear();
		return "cart";
	}
}
