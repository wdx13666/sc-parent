package com.sc.sellergoods.service.impl;

import com.sc.pojo.Cities;
import com.sc.sellergoods.service.CitiesService;
import com.sc.mapper.CitiesMapper;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 行政区域地州市信息表 服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
@Service
public class CitiesServiceImpl extends ServiceImpl<CitiesMapper, Cities> implements CitiesService {

}
