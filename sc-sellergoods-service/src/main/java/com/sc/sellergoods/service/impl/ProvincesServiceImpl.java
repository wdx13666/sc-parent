package com.sc.sellergoods.service.impl;

import com.sc.pojo.Provinces;
import com.sc.sellergoods.service.ProvincesService;
import com.sc.mapper.ProvincesMapper;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 省份信息表 服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
@Service
public class ProvincesServiceImpl extends ServiceImpl<ProvincesMapper, Provinces> implements ProvincesService {

}
