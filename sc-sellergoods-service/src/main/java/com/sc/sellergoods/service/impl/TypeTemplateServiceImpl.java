package com.sc.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sc.entity.PageResult;
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
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	/**
	 * 将数据存入缓存
	 */
	private void saveToRedis(){
		//获取模板数据
		List<TypeTemplate> typeTemplateList = baseMapper.selectList(null);
		//循环模板
		for(TypeTemplate typeTemplate :typeTemplateList){				
			//存储品牌列表		
			List<Map> brandList = JSON.parseArray(typeTemplate.getBrandIds(), Map.class);			
			redisTemplate.boundHashOps("brandList").put(typeTemplate.getId(), brandList);
			//存储规格列表
			List<Map> specList = findSpecList(typeTemplate.getId());//根据模板ID查询规格列表
			redisTemplate.boundHashOps("specList").put(typeTemplate.getId(), specList);		
		}
	}

	@Override
	public PageResult findPage(int page, int rows) {
		List<TypeTemplate> pages = baseMapper.selectPage(new RowBounds(page, rows), null);
		saveToRedis();//存入数据到缓存
		return new PageResult(pages.size(), pages);
	}
}
