package com.sc.manager.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sc.entity.GoodsZ;
import com.sc.entity.PageResult;
import com.sc.entity.Result;
import com.sc.pojo.Goods;
import com.sc.pojo.GoodsDesc;
import com.sc.sellergoods.service.GoodsDescService;
import com.sc.sellergoods.service.GoodsService;

/**
 * controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

	@Reference
	private GoodsService goodsService;
	@Reference
	private GoodsDescService descService;

	/**
	 * 返回全部列表
	 * 
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<Goods> findAll() {
		return goodsService.selectList(null);
	}

	/**
	 * 返回全部列表
	 * 
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows) {
		// 添加查询条件
		Page<Goods> pages = goodsService.selectPage(new Page<Goods>(page, rows));
		return new PageResult(pages.getTotal(), pages.getRecords());
	}

	/**
	 * 增加
	 * 
	 * @param Goods
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody GoodsZ goodsZ) {
		// 获取登录名
		String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
		goodsZ.getGoods().setSellerId(sellerId);// 设置商家ID
		try {
			goodsService.add(goodsZ);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}

	/**
	 * 修改
	 * 
	 * @param Goods
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody Goods Goods) {
		try {
			goodsService.updateById(Goods);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}

	/**
	 * 获取实体
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public GoodsZ findOne(String id) {
		GoodsZ goodsZ = new GoodsZ();
		Goods goods = goodsService.selectById(id);
		goodsZ.setGoods(goods);
		GoodsDesc desc = descService.selectById(id);
		goodsZ.setDesc(desc);
		return goodsZ;
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long[] ids) {
		try {
			goodsService.deleteBatchIds(Arrays.asList(ids));
			return new Result(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}

	/**
	 * 查询+分页
	 * 
	 * @param brand
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody Goods Goods, int page, int rows) {
		Page<Goods> pages = goodsService.selectPage(new Page<Goods>(page, rows), new EntityWrapper<Goods>(Goods));
		return new PageResult(pages.getTotal(), pages.getRecords());
	}

	@RequestMapping("/updateStatus")
	public Result updateStatus(Long [] ids,String status){
		try {
			List<Goods> batchIds = goodsService.selectBatchIds(Arrays.asList(ids));
			goodsService.updateBatchById(batchIds);
			return  new Result(true, "成功 ");
			
		} catch (Exception e) {
			e.printStackTrace();
			return  new Result(false, "失败 ");

		}
	}
}
