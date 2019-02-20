package com.sc.sellergoods.service.impl;

import com.sc.pojo.Content;
import com.sc.sellergoods.service.ContentService;
import com.sc.mapper.ContentMapper;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

}
