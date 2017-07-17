package com.hchedu.shop.service.Impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hchedu.shop.dao.ProductMapper;
import com.hchedu.shop.entities.Product;
import com.hchedu.shop.service.ProductService;
import com.hchedu.shop.utils.PageBean;
@Transactional
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public List<Product> findProduct(Integer min,Integer max) {
		List<Product> hList=productMapper.queryProductByHotAndNew(min,max);
		return hList;
	}

	@Override
	public PageBean<Product> findPageByCid(Integer cid, Integer page) {
		PageBean<Product> pageBean= new PageBean<>();
		pageBean.setPage(page);
		int limit=12;
		pageBean.setLimit(limit);
		int begin=(page-1)*limit;
		List<Product> list=productMapper.selectPageByCid(cid,begin,limit);
		pageBean.setList(list);
		int totalCount=productMapper.countByCid(cid);
		pageBean.setTotalCount(totalCount);
		int totalPage = 
		totalCount%limit == 0 ? totalCount/limit : totalCount/limit+1;
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

	@Override
	public PageBean<Product> findPageByCsid(Integer csid, Integer page) {
		PageBean<Product> pageBean= new PageBean<>();
		pageBean.setPage(page);
		int limit=12;
		pageBean.setLimit(limit);
		int begin=(page-1)*limit;
		List<Product> list=productMapper.selectPageByCsid(csid,begin,limit);
		pageBean.setList(list);
		int totalCount=productMapper.countByCsid(csid);
		pageBean.setTotalCount(totalCount);
		int totalPage = 
		totalCount%limit == 0 ? totalCount/limit : totalCount/limit+1;
		pageBean.setTotalPage(totalPage);
		return pageBean;
		
	}

	@Override
	public Product findProductByPid(Integer pid) {
		return productMapper.selectByPrimaryKey(pid);		 
	}

	@Override
	public PageBean<Product> seachProduct(String proCategory, Double proPriceMin, Double proPriceMax, String proName,
			Integer page) {
		PageBean<Product> pageBean= new PageBean<>();
		pageBean.setPage(page);
		int limit=12;
		pageBean.setLimit(limit);
		int begin=(page-1)*limit;
		List<Product> list=productMapper.seachProduct(proCategory,proPriceMin,proPriceMax,proName,begin,limit);
		pageBean.setList(list);
		int totalCount=list.size();
		System.out.println(totalCount);
		pageBean.setTotalCount(totalCount);
		int totalPage = 
		totalCount%limit == 0 ? totalCount/limit : totalCount/limit+1;
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

}
