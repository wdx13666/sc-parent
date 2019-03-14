package com.sc.sellergoods.service;

import com.sc.entity.PageResult;
import com.sc.pojo.TypeTemplate;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
public interface TypeTemplateService extends IService<TypeTemplate> {

	public List<Map> findSpecList(Long id);
	public PageResult  findPage(int page,int rows);

}
