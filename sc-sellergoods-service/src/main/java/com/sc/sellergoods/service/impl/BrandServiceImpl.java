package com.sc.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sc.mapper.BrandMapper;
import com.sc.pojo.Brand;
import com.sc.sellergoods.service.BrandService;

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

	@Override
	public List<Map> selectOptionList() {
		return baseMapper.selectOptionList();
	}

}
