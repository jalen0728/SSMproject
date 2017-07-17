package com.hchedu.shop.service;

import java.util.List;

import com.hchedu.shop.entities.Product;
import com.hchedu.shop.utils.PageBean;

public interface ProductService {
	List<Product> findProduct(Integer min,Integer max);

	PageBean<Product> findPageByCid(Integer cid, Integer page);

	PageBean<Product> findPageByCsid(Integer csid, Integer page);

	Product findProductByPid(Integer pid);

	PageBean<Product> seachProduct(String proCategory, Double proPriceMin, Double proPriceMax, String proName,
			Integer page);
}
