package com.sc.sellergoods.service.impl;

import com.sc.pojo.OrderItem;
import com.sc.sellergoods.service.OrderItemService;
import com.sc.mapper.OrderItemMapper;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}
