package com.sc.sellergoods.service;

import com.sc.pojo.ItemCat;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 商品类目 服务类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
public interface ItemCatService extends IService<ItemCat> {

	public List<ItemCat> findByParentId(Long parentId);

}
