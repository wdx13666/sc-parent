package com.sc.sellergoods.service.impl;

import com.sc.pojo.ContentCategory;
import com.sc.sellergoods.service.ContentCategoryService;
import com.sc.mapper.ContentCategoryMapper;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 内容分类 服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
@Service
public class ContentCategoryServiceImpl extends ServiceImpl<ContentCategoryMapper, ContentCategory> implements ContentCategoryService {

}
