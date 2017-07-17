package com.hchedu.shop.entities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart {
	private Double total;
	
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Map<Integer, CartItems> getCarts() {
		return carts;
	}

	public void setCarts(Map<Integer, CartItems> carts) {
		this.carts = carts;
	}

	private Map<Integer,CartItems> carts=new LinkedHashMap<>();
	
	public List<CartItems> getCartItems(){
		return new ArrayList<>(carts.values());
	} 
	
	public void addCartItems(CartItems cartItems){
		Integer pid=cartItems.getProduct().getPid();
		if(carts.containsKey(pid)){
			carts.get(pid).setCount(carts.get(pid).getCount()+cartItems.getCount());
		}else{
			carts.put(pid, cartItems);
		}
	}
}
