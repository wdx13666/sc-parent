package com.sc.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.sc.entity.Specifications;
import com.sc.pojo.Specification;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
public interface SpecificationService extends IService<Specification> {
	public void insertByO(Specifications specification);
	public List<Map> selectOptionList();
}
