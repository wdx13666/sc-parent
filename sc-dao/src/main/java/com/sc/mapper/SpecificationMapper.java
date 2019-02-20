package com.sc.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sc.pojo.Specification;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
public interface SpecificationMapper extends BaseMapper<Specification> {

	public void insertByO(Specification specification);
	
	public List<Map> selectOptionList();
}
