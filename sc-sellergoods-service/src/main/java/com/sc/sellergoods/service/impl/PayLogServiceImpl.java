package com.sc.sellergoods.service.impl;

import com.sc.pojo.PayLog;
import com.sc.sellergoods.service.PayLogService;
import com.sc.mapper.PayLogMapper;
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
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

}
