package com.sc.sellergoods.service.impl;

import com.sc.pojo.Seller;
import com.sc.sellergoods.service.SellerService;
import com.sc.mapper.SellerMapper;
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
public class SellerServiceImpl extends ServiceImpl<SellerMapper, Seller> implements SellerService {

}
