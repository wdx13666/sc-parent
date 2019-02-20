package com.sc.shop.controller;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.plugins.Page;
import com.sc.entity.PageResult;
import com.sc.entity.Result;
import com.sc.pojo.TypeTemplate;
import com.sc.sellergoods.service.TypeTemplateService;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {

	@Reference
	private TypeTemplateService templateService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TypeTemplate> findAll(){			
		return templateService.selectList(null);
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){	
		Page<TypeTemplate> pages = templateService.selectPage(new Page<TypeTemplate>(page, rows));
		return new PageResult(pages.getTotal(), pages.getRecords());
	}
	
	/**
	 * 增加
	 * @param TypeTemplate
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TypeTemplate typeTemplate){
		try {
			templateService.insert(typeTemplate);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param brand
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TypeTemplate typeTemplate){
		try {
			templateService.updateById(typeTemplate);
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
	public TypeTemplate findOne(Long id){
		return templateService.selectById(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			templateService.deleteBatchIds(Arrays.asList(ids));
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param brand
	 * @param page
	 * @param rows
	 * @return
	 */
	/*@RequestMapping("/search")
	public PageResult search(@RequestBody Brand brand, int page, int rows  ){
		Page<Brand> pages = brandService.selectPage(new Page<Brand>(page, rows),new EntityWrapper<Brand>(brand));
		return new PageResult(pages.getTotal(), pages.getRecords());
	}*/
	
	
	@RequestMapping("/findSpecList")
	public List<Map> findSpecList(Long id){
		return templateService.findSpecList(id);
	}
	
}
