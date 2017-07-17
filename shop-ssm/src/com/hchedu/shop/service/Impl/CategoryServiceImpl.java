package com.hchedu.shop.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hchedu.shop.dao.CategoryMapper;
import com.hchedu.shop.dao.CategorySecondMapper;
import com.hchedu.shop.entities.Category;
import com.hchedu.shop.entities.CategorySecond;
import com.hchedu.shop.service.CategoryService;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private CategorySecondMapper categorySecondMapper;
	
	@Override
	public List<Category> findAllCategory() {
		return	categoryMapper.selectByExample(null);
		
	}

	@Override
	public List<Category> findCAndCs() {
		List<Category> List=findAllCategory();
		for (Category c : List) {
			Integer cid=c.getCid();
			List<CategorySecond> csList=categorySecondMapper.selectByCid(cid);
			c.setCategorySeconds(csList);
		}
		return List;
	}

	

	
	
}
