package com.sc.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.sc.entity.PageResult;
import com.sc.pojo.TbBrand;

/**
 * 品牌服务层接口
 * @author Administrator
 *
 */
public interface BrandService {
	
	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbBrand> findAll();
	
	/**
	 * 返回列表（分页）
	 * @return
	 */
	public PageResult findPage(TbBrand tbBrand,Integer pageNum,Integer pageSize);
	
	public PageResult findPage(Integer pageNum,Integer pageSize);
	
	/**
	 * 新增品牌
	 * @param brand
	 */
	public void add(TbBrand brand);
	
	/**
	 * 根据Id查找品牌 
	 * @param id
	 * @return
	 */
	public TbBrand findOneById(Long id);
	
	/**
	 * 修改品牌
	 * @param brand
	 */
	public void update(TbBrand brand);
	
	/**
	 * 删除品牌
	 * @param id
	 */
	public void delete(Long[] ids);
	
	/**
	 * 品牌下拉框数据
	 * @return
	 */
	public List<Map> selectOptionList();
}
