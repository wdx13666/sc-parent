package com.sc.cart.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sc.entity.Result;
import com.sc.pojo.Order;
import com.sc.sellergoods.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Reference
	private OrderService orderService;


	/**
	 * 增加
	 * @param order
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody Order order){
		//获取当前登录人账号
		order.setUserId("lijialong");
		order.setSourceType("2");//订单来源  PC
		try {
			orderService.add(order);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}

}
