package com.sc.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.sc.pojo.Item;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
public interface ItemSearchService extends IService<Item> {

	/**
	 * 搜索
	 * @param keywords
	 * @return
	 */
	public Map<String,Object> search(Map searchMap);
	
	/**
	 * 导入列表
	 * @param list
	 */
	public void importList(List list);
	
	/**
	 * 删除商品列表
	 * @param goodsIds  (SPU)
	 */
	public void deleteByGoodsIds(List goodsIds);
}
