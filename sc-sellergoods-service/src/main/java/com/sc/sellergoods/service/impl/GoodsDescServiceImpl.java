package com.sc.sellergoods.service.impl;

import com.sc.pojo.GoodsDesc;
import com.sc.sellergoods.service.GoodsDescService;
import com.sc.mapper.GoodsDescMapper;
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
public class GoodsDescServiceImpl extends ServiceImpl<GoodsDescMapper, GoodsDesc> implements GoodsDescService {

}
