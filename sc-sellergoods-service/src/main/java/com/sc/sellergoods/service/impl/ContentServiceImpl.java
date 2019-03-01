package com.sc.sellergoods.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sc.mapper.ContentMapper;
import com.sc.pojo.Content;
import com.sc.sellergoods.service.ContentService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public List<Content> findByCategoryId(Long categoryId) {
		List<Content> contents = (List<Content>) redisTemplate.boundHashOps("content").get(categoryId);
		if (contents == null) {
			contents = baseMapper.selectList(
					new EntityWrapper<Content>().eq("category_id", categoryId).eq("status", "1").orderBy("sort_order"));
			redisTemplate.boundHashOps("content").put(categoryId, contents);
		} else {
			System.err.println("从缓存中读取数据................");
		}
		return contents;
	}

	@Override
	public void add(Content content) {
		baseMapper.insert(content);
		// 清除缓存
		redisTemplate.boundHashOps("content").delete(content.getCategoryId());
	}

	@Override
	public void delete(Long [] ids) {
		List<Content> list = baseMapper.selectBatchIds(Arrays.asList(ids));
		redisTemplate.boundHashOps("content").delete(list);
		baseMapper.deleteBatchIds(Arrays.asList(ids));
	}

	@Override
	public void update(Content content) {
		//查询修改前的分类Id
		Long categoryId = baseMapper.selectById(content.getId()).getCategoryId();
		redisTemplate.boundHashOps("content").delete(categoryId);
		baseMapper.updateById(content);
		//如果分类ID发生了修改,清除修改后的分类ID的缓存
		if(categoryId.longValue()!=content.getCategoryId().longValue()){
			redisTemplate.boundHashOps("content").delete(content.getCategoryId());
		}	

	}

}
