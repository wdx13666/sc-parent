package com.sc.user.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sc.entity.Result;
import com.sc.pojo.User;
import com.sc.sellergoods.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@RequestMapping("/add")
	public Result add(User user){
		try {
			user.setCreated(new Date());//创建日期
			user.setUpdated(new Date());//修改日期
			String md5Hex = org.apache.commons.codec.digest.DigestUtils.md5Hex(user.getPassword());//对密码加密
			user.setPassword(md5Hex);
			service.insert(user);
			return new Result(true, "注册成功!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Result(false, "注册失败!");
		}
	}
	
}
