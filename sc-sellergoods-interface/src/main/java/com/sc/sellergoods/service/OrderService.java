package com.sc.sellergoods.service;

import com.baomidou.mybatisplus.service.IService;
import com.sc.pojo.Order;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
public interface OrderService extends IService<Order> {

	public void add(Order order);
}
