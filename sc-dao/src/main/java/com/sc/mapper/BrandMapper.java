package com.sc.mapper;

import com.sc.pojo.Brand;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
public interface BrandMapper extends BaseMapper<Brand> {
	public List<Map> selectOptionList();
}
