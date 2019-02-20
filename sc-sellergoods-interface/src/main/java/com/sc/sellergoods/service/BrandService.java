package com.sc.sellergoods.service;

import com.sc.pojo.Brand;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
public interface BrandService extends IService<Brand> {
	public List<Map> selectOptionList();
}
