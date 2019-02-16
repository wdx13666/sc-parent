package com.sc.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sc.entity.PageResult;
import com.sc.mapper.TbBrandMapper;
import com.sc.pojo.TbBrand;
import com.sc.pojo.TbBrandExample;
import com.sc.pojo.TbBrandExample.Criteria;
import com.sc.sellergoods.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{

	@Autowired
	private TbBrandMapper brandMapper;
	
	@Override
	public List<TbBrand> findAll() {
		List<TbBrand> list = brandMapper.selectByExample(null);
		return list;
	}

	@Override
	public PageResult findPage(TbBrand tbBrand,Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		TbBrandExample brandExample = new TbBrandExample();
		Criteria criteria = brandExample.createCriteria();
		if(tbBrand != null){
			if(tbBrand.getName() != null && tbBrand.getName().length() > 0){
				criteria.andNameLike("%"+ tbBrand.getName() +"%");
			}
			
			if(tbBrand.getFirstChar() != null && tbBrand.getFirstChar().length() > 0){
				criteria.andFirstCharLike("%" + tbBrand.getFirstChar() + "%");
			}
		}
		Page<TbBrand> page = (Page<TbBrand>)brandMapper.selectByExample(brandExample);
		return new PageResult(page.getTotal(), page.getResult());
	}


	@Override
	public void add(TbBrand brand) {
		brandMapper.insert(brand);
	}

	@Override
	public TbBrand findOneById(Long id) {
		
		return brandMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(TbBrand brand) {
		brandMapper.updateByPrimaryKey(brand);
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			brandMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public PageResult findPage(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		Page<TbBrand> page = (Page<TbBrand>)brandMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public List<Map> selectOptionList() {
		
		return brandMapper.selectOptionList();
	}

	
}
