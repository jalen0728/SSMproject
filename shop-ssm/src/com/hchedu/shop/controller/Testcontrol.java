package com.hchedu.shop.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hchedu.shop.entities.Category;
import com.hchedu.shop.entities.Product;
import com.hchedu.shop.service.CategoryService;
import com.hchedu.shop.service.ProductService;

@Controller
public class Testcontrol {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	
	/*@RequestMapping("/index")
	public String tets(HttpSession session){
		List<Category> cList =categoryService.findAllCategory();
		session.setAttribute("cList", cList);
		List<Product> hList=productService.findProduct(1, 10);
		session.setAttribute("hList", hList);
		List<Product> nList=productService.findProduct(11, 20);
		session.setAttribute("nList", nList);
		return "index";
	}*/
	
	@RequestMapping("/index")
	public String tets(HttpSession session,Map<String,Object> map){
		List<Category> List=categoryService.findCAndCs();
		session.setAttribute("cList", List);
		List<Product> hList=productService.findProduct(1, 10);
		session.setAttribute("hList", hList);
		List<Product> nList=productService.findProduct(11, 20);
		session.setAttribute("nList", nList);
		return "index";
	}
}
