package com.hchedu.shop.service;

import java.util.List;

import com.hchedu.shop.entities.Category;
import com.hchedu.shop.entities.CategorySecond;

public interface CategoryService {
	
	List<Category> findAllCategory();

	List<Category>  findCAndCs();

}
