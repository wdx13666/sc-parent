package com.sc.sellergoods.service.impl;

import com.sc.pojo.ItemCat;
import com.sc.sellergoods.service.ItemCatService;
import com.sc.mapper.ItemCatMapper;
import com.alibaba.dubbo.config.annotation.Service;
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

}
