package com.sc.cart.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sc.pojo.Address;
import com.sc.sellergoods.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Reference
	private AddressService addressService;


	@RequestMapping("/findListByLoginUser")
	public List<Address> findListByLoginUser(){
		return addressService.selectList(new EntityWrapper<Address>().eq("user_id", "lijialong"));
	}

}
