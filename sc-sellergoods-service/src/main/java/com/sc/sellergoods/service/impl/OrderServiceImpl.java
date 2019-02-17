package com.sc.sellergoods.service.impl;

import com.sc.pojo.Order;
import com.sc.sellergoods.service.OrderService;
import com.sc.mapper.OrderMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
