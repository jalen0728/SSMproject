package com.hchedu.shop.entities;

public class CartItems {
	private Double subtotal;
	private Product product;
	private Integer count;
	public Double getSubtotal() {
		return count*product.getShopPrice();
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	
	
}
