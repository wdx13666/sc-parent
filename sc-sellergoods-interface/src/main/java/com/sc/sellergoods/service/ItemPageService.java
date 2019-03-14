package com.sc.sellergoods.service;

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
public interface ItemPageService extends IService<Item> {
	
	public boolean getItemHtml(Long goodsId);
	
	public boolean deleteItemHtml(Long[] goodsIds);
}
