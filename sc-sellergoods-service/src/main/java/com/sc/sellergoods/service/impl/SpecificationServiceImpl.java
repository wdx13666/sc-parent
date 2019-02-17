package com.sc.sellergoods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sc.entity.Specifications;
import com.sc.mapper.SpecificationMapper;
import com.sc.mapper.SpecificationOptionMapper;
import com.sc.pojo.Specification;
import com.sc.pojo.SpecificationOption;
import com.sc.sellergoods.service.SpecificationService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
@Service
public class SpecificationServiceImpl extends ServiceImpl<SpecificationMapper, Specification> implements SpecificationService {

	@Autowired
	private SpecificationOptionMapper mapper;
	
	@Override
	public void insertByO(Specifications specification) {
		
		baseMapper.insertByO(specification.getSpecification());//插入规格	
		//循环插入规格选项
		for (SpecificationOption specificationOption : specification.getSpecificationOptionList()) {
			specificationOption.setSpecId(specification.getSpecification().getId());
			mapper.insert(specificationOption);
		}
		
	}

}
