package com.sc.shop.controller;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sc.entity.PageResult;
import com.sc.entity.Result;
import com.sc.pojo.ItemCat;
import com.sc.sellergoods.service.ItemCatService;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/itemCat")
public class ItemCatContoller {

	@Reference
	private ItemCatService itemCatService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<ItemCat> findAll(){			
		return itemCatService.selectList(null);
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){	
		Page<ItemCat> pages = itemCatService.selectPage(new Page<ItemCat>(page, rows));
		return new PageResult(pages.getTotal(), pages.getRecords());
	}
	
	/**
	 * 增加
	 * @param ItemCat
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody ItemCat ItemCat){
		try {
			itemCatService.insert(ItemCat);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param ItemCat
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody ItemCat ItemCat){
		try {
			itemCatService.updateById(ItemCat);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public ItemCat findOne(Long id){
		return itemCatService.selectById(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			itemCatService.deleteBatchIds(Arrays.asList(ids));
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param ItemCat
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody ItemCat ItemCat, int page, int rows  ){
		Page<ItemCat> pages = itemCatService.selectPage(new Page<ItemCat>(page, rows),new EntityWrapper<ItemCat>(ItemCat));
		return new PageResult(pages.getTotal(), pages.getRecords());
	}
	
	@RequestMapping("/findByParentId")
	public List<ItemCat> findByParentId(Long parentId){
		return itemCatService.selectList(new EntityWrapper<ItemCat>().eq("parent_id", parentId));
	}

}
