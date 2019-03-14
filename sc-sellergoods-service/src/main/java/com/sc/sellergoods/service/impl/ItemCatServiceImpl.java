package com.sc.sellergoods.service.impl;

import com.sc.pojo.ItemCat;
import com.sc.sellergoods.service.ItemCatService;
import com.sc.mapper.ItemCatMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 商品类目 服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
@Service
public class ItemCatServiceImpl extends ServiceImpl<ItemCatMapper, ItemCat> implements ItemCatService {

	@Autowired
	private RedisTemplate redisTemplate;
	
	@Override
	public List<ItemCat> findByParentId(Long parentId) {
		//每次执行查询的时候，一次性读取缓存进行存储 (因为每次增删改都要执行此方法)
		List<ItemCat> list = baseMapper.selectList(null);
		for (ItemCat itemCat : list) {
			redisTemplate.boundHashOps("itemCat").put(itemCat.getName(), itemCat.getTypeId());
		}
		System.out.println("更新缓存:商品分类表");
		baseMapper.selectList(new EntityWrapper<ItemCat>().eq("parent_id", parentId));
		return null;
	}
	
	
}
