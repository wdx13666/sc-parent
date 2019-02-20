package com.sc.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sc.mapper.SpecificationOptionMapper;
import com.sc.mapper.TypeTemplateMapper;
import com.sc.pojo.SpecificationOption;
import com.sc.pojo.TypeTemplate;
import com.sc.sellergoods.service.TypeTemplateService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
@Service
public class TypeTemplateServiceImpl extends ServiceImpl<TypeTemplateMapper, TypeTemplate> implements TypeTemplateService {
	
	@Autowired
	private SpecificationOptionMapper specificationOptionMapper;
	
	@Override
	public List<Map> findSpecList(Long id){
		//查询模板
		TypeTemplate template = baseMapper.selectById(id);
		List<Map> list = JSON.parseArray(template.getSpecIds(), Map.class);
		for (Map map : list) {
			//查询规格选项列表
			List<SpecificationOption> selectList = specificationOptionMapper.selectList(new EntityWrapper<SpecificationOption>().eq("spec_id", new Long((Integer)map.get("id"))));
			map.put("options", selectList);
		}
		return list;
	}
}
