package com.sc.shop.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sc.entity.PageResult;
import com.sc.entity.Result;
import com.sc.pojo.Seller;
import com.sc.sellergoods.service.SellerService;

/**
 * controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

	@Reference
	private SellerService sellerService;

	/**
	 * 返回全部列表
	 * 
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<Seller> findAll() {
		return sellerService.selectList(null);
	}

	/**
	 * 返回全部列表
	 * 
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows) {
		Page<Seller> pages = sellerService.selectPage(new Page<Seller>(page, rows));
		return new PageResult(pages.getTotal(), pages.getRecords());
	}

	/**
	 * 增加
	 * 
	 * @param seller
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody Seller seller) {
		// 密码加密
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode(seller.getPassword());
		seller.setPassword(password);
		try {
			seller.setStatus("0");
			seller.setCreateTime(new Date());
			sellerService.insert(seller);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}

	/**
	 * 修改
	 * 
	 * @param seller
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody Seller seller) {
		try {
			sellerService.updateById(seller);
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
	public Seller findOne(String id) {
		return sellerService.selectById(id);
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
			sellerService.deleteBatchIds(Arrays.asList(ids));
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
	public PageResult search(@RequestBody Seller seller, int page, int rows) {
		Page<Seller> pages = sellerService.selectPage(new Page<Seller>(page, rows), new EntityWrapper<Seller>(seller));
		return new PageResult(pages.getTotal(), pages.getRecords());
	}

	/**
	 * 更改状态
	 * 
	 * @param sellerId
	 *            商家ID
	 * @param status
	 *            状态
	 */
	@RequestMapping("/updateStatus")
	public Result updateStatus(String sellerId, String status) {
		try {
			Seller seller = sellerService.selectById(sellerId);
			seller.setStatus(status);
			sellerService.updateById(seller);
			return new Result(true, "成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "失败");
		}
	}
}
