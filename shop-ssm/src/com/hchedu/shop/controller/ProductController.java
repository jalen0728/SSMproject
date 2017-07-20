package com.hchedu.shop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hchedu.shop.entities.Category;
import com.hchedu.shop.entities.Product;
import com.hchedu.shop.service.CategoryService;
import com.hchedu.shop.service.ProductService;
import com.hchedu.shop.utils.PageBean;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	//一级菜单
	@RequestMapping("/product_findByCid")
	public String productFindByCid(@RequestParam("cid") Integer cid,
			@RequestParam(value="page",required=false,defaultValue="1") Integer page, Map<String,Object> map){
		PageBean<Product> pageBean=productService.findPageByCid(cid,page);
		map.put("pageBean", pageBean);
		map.put("cid", cid);
		return "productList";
	}
	//二级菜单
	@RequestMapping("/product_findByCsid")
	public String productFindByCsid(@RequestParam("csid") Integer csid,
			@RequestParam(value="page",required=false,defaultValue="1") Integer page, Map<String,Object> map){
		PageBean<Product> pageBean=productService.findPageByCsid(csid,page);
		List<Category> List= categoryService.findCAndCs();
		map.put("cList", List);
		map.put("pageBean", pageBean);
		map.put("csid", csid);
		return "productList";
	}
	//点击图片
	@RequestMapping("/product_findByPid")
	public String productFindByPid(@RequestParam("pid")Integer pid,Map<String ,Object> map){
		Product product=productService.findProductByPid(pid);
		List<Category> List= categoryService.findCAndCs();
		map.put("cList", List);
		map.put("model", product);
		return "product";
	}
	//搜索商品
	@RequestMapping("/seachProduct")
	public String seachProduct(@RequestParam("proCategory") String proCategory,
			@RequestParam("proPriceMin") Double proPriceMin,@RequestParam("proPriceMax") Double proPriceMax,
			@RequestParam("proName") String proName,
			@RequestParam(value="page",required=false,defaultValue="1") Integer page,Map<String,Object> map){
		PageBean<Product> pageBean=productService.seachProduct(proCategory,proPriceMin,proPriceMax,proName,page);
		//List<Category> List= categoryService.findCAndCs();
		//map.put("cList", List);
		map.put("pageBean", pageBean);	
		return "productList";
	}
}
