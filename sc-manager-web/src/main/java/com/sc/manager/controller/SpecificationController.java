package com.sc.manager.controller;

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
import com.sc.entity.Specifications;
import com.sc.pojo.SpecificationOption;
import com.sc.pojo.Specification;
import com.sc.sellergoods.service.SpecificationOptionService;
import com.sc.sellergoods.service.SpecificationService;

/**
 * controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/specification")
public class SpecificationController {

	@Reference
	private SpecificationService specificationService;
	@Reference
	private SpecificationOptionService optionService;

	/**
	 * 返回全部列表
	 * 
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<Specification> findAll() {
		return specificationService.selectList(null);
	}

	/**
	 * 返回全部列表
	 * 
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows) {
		Page<Specification> pages = specificationService.selectPage(new Page<Specification>(page, rows));
		return new PageResult(pages.getTotal(), pages.getRecords());
	}

	/**
	 * 增加
	 * 
	 * @param specification
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody Specifications specification) {
		try {
			specificationService.insertByO(specification);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}

	/**
	 * 修改
	 * 
	 * @param specification
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody Specifications specification) {
		try {
			specificationService.updateById(specification.getSpecification());
			//删除原有的规格选项	
			optionService.delete(new EntityWrapper<SpecificationOption>().eq("spec_id", specification.getSpecification().getId()));
			//循环插入规格选项
			for(SpecificationOption specificationOption:specification.getSpecificationOptionList()){			
				specificationOption.setSpecId(specification.getSpecification().getId());
				optionService.insert(specificationOption);		
			}	

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
	public Specifications findOne(Long id) {
		Specification tbSpecification = specificationService.selectById(id);
		// 查询规格选项列表
		List<SpecificationOption> selectList = optionService
				.selectList(new EntityWrapper<SpecificationOption>().eq("spec_id", id));
		// 构建组合实体类返回结果
		Specifications spec = new Specifications();
		spec.setSpecification(tbSpecification);
		spec.setSpecificationOptionList(selectList);
		return spec;

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
			specificationService.deleteBatchIds(Arrays.asList(ids));
			//删除原有的规格选项
			for (Long id : ids) {
				optionService.delete(new EntityWrapper<SpecificationOption>().eq("spec_id", id));
			}
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
	/*
	 * @RequestMapping("/search") public PageResult search(@RequestBody
	 * TbSpecification specification, int page, int rows ){ return
	 * specificationService.findPage(specification, page, rows); }
	 * 
	 * @RequestMapping("/selectOptionList") public List<Map> selectOptionList(){
	 * return specificationService.selectOptionList(); }
	 */

}
