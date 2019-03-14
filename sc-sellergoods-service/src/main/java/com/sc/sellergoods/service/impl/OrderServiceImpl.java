package com.sc.sellergoods.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sc.entity.Cart;
import com.sc.mapper.OrderItemMapper;
import com.sc.mapper.OrderMapper;
import com.sc.pojo.Order;
import com.sc.pojo.OrderItem;
import com.sc.sellergoods.service.OrderService;
import com.sc.utils.IdWorker;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private OrderItemMapper orderItemMapper;

	@Autowired
	private IdWorker idWorker;

	@Override
	public void add(Order order) {
		// 得到购物车数据
		List<Cart> cartList = (List<Cart>) redisTemplate.boundHashOps("cartList").get("wdx");
		for (Cart cart : cartList) {
			long orderId = idWorker.nextId();
			System.out.println("sellerId:" + cart.getSellerId());
			Order tborder = new Order();// 新创建订单对象
			tborder.setOrderId(orderId);// 订单ID
			tborder.setUserId(order.getUserId());// 用户名
			tborder.setPaymentType(order.getPaymentType());// 支付类型
			tborder.setStatus("1");// 状态：未付款
			tborder.setCreateTime(new Date());// 订单创建日期
			tborder.setUpdateTime(new Date());// 订单更新日期
			tborder.setReceiverAreaName(order.getReceiverAreaName());// 地址
			tborder.setReceiverMobile(order.getReceiverMobile());// 手机号
			tborder.setReceiver(order.getReceiver());// 收货人
			tborder.setSourceType(order.getSourceType());// 订单来源
			tborder.setSellerId(cart.getSellerId());// 商家ID
			// 循环购物车明细
			double money = 0;
			for (OrderItem orderItem : cart.getOrderItemList()) {
				orderItem.setId(idWorker.nextId());
				orderItem.setOrderId(orderId);// 订单ID
				orderItem.setSellerId(cart.getSellerId());
				money += orderItem.getTotalFee().doubleValue();// 金额累加
				orderItemMapper.insert(orderItem);
			}
			tborder.setPayment(new BigDecimal(money));
			baseMapper.insert(tborder);
		}
		redisTemplate.boundHashOps("cartList").delete(order.getUserId());
	}

}
