package com.hchedu.shop.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hchedu.shop.entities.User;
import com.hchedu.shop.service.UserService;
import com.hchedu.shop.utils.MailUitls;
import com.hchedu.shop.utils.UUIDUtils;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	//跳转登录页面
	@RequestMapping("/user_loginPage")
	public String loginPage(){	
		return "login";
	}
	//登录校验
		@RequestMapping("/user_login")
		public ModelAndView checkLogin(@RequestParam("username") String username,
			@RequestParam("password") String password,HttpSession session){	
			ModelAndView mav= new ModelAndView();
			User user=userService.seclectUserByUserName(username);
			if(user!=null&&user.getPassword().equals(password)&&user.getState()==1){
				mav.setViewName("index");
				session.setAttribute("existUser", user);
			}else if(user!=null&&user.getPassword().equals(password)&&user.getState()==0){
				MailUitls.sendMail(user.getEmail(), user.getCode());
				mav.addObject("registMsg", "账号未激活,请去邮箱激活！");
				mav.setViewName("msg");
			}else {
				mav.setViewName("login");
				mav.addObject("loginFail", "用户名或密码错误");
			}
			return mav;
		}
	//退出登录
	@RequestMapping("/user_exit")
	public String exit(HttpSession session){
		session.invalidate();
		return "redirect:index";
	}
	//跳转注册页面
	@RequestMapping("/user_registPage")
	public String go(){	
		return "regist";
	}
	//校验用户名是否存在
	@ResponseBody
	@RequestMapping("/checkUsername")
	public String checkUsername(@RequestParam("username") String username){	
		return userService.checkUername(username);
	}
	//校验注册提交是否能通过
	@RequestMapping("/user_regist")
	public ModelAndView UsrRegist(User user,@RequestParam("checkcode") String checkcode,
		HttpSession session,Map<String,Object> map){
		ModelAndView mav= new ModelAndView();
		String sessionCode=(String) session.getAttribute("checkCode");
		boolean flag=checkcode.equalsIgnoreCase(sessionCode);
		if(!flag){
			mav.addObject("checkCodeError", "验证码错误");
			mav.setViewName("regist");
			return mav;
		}
		user.setState(0);
		user.setCode(UUIDUtils.getUUID());
		
		userService.save(user);
		
		MailUitls.sendMail(user.getEmail(), user.getCode());
		
		mav.addObject("registMsg", "注册成功，请去邮箱激活！");
		mav.setViewName("msg");
		return mav;
	}
	//校验激活是否成功
	@RequestMapping("/user_active")
	public String  userActive(@RequestParam("code") String code,
		Map<String,Object> map){
		User user=userService.findUserByCode(code);
		System.out.println(user);
		if(user==null){
			map.put("activeMsg", "激活失败,用户不存在活在激活码错误");
			return "msg";
		}
		user.setState(1);
		user.setCode(null);
		userService.modify(user);	
		map.put("activeMsg", "激活成功!请登录！！");
		return "msg";
		
	}
	
}	
