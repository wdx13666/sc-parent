package com.sc.sellergoods.service.impl;

import com.sc.pojo.Brand;
import com.sc.sellergoods.service.BrandService;
import com.sc.mapper.BrandMapper;
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
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

}
