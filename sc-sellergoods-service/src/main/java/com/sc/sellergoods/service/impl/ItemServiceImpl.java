package com.sc.sellergoods.service.impl;

import com.sc.pojo.Item;
import com.sc.sellergoods.service.ItemService;
import com.sc.mapper.ItemMapper;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

}
