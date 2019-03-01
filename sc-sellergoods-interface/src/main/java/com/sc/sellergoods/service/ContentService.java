package com.sc.sellergoods.service;

import com.sc.pojo.Content;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
public interface ContentService extends IService<Content> {

	public List<Content> findByCategoryId(Long categoryId);
	public void add(Content content);
	public void delete(Long[] ids);
	public void update(Content content);
}
