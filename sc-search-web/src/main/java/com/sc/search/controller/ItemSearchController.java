package com.sc.search.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sc.sellergoods.service.ItemSearchService;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/itemsearch")
public class ItemSearchController {

	@Reference
	private ItemSearchService itemSearchService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/search")
	public Map<String, Object> findAll(@RequestBody Map searchMap){			
		return itemSearchService.search(searchMap);
	}
	
	
	

}
