package com.sc.sellergoods.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.GroupOptions;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.HighlightQuery;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.GroupEntry;
import org.springframework.data.solr.core.query.result.GroupPage;
import org.springframework.data.solr.core.query.result.GroupResult;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sc.mapper.ItemMapper;
import com.sc.pojo.Item;
import com.sc.sellergoods.service.ItemSearchService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
@Service
public class ItemSearchServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemSearchService {

	@Autowired
	private SolrTemplate solrTemplate;
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public Map<String, Object> search(Map searchMap) {
		Map<String, Object> map = new HashMap<>();
		// 1.查询列表
		map.putAll(searchList(searchMap));
		// 2.分组查询 商品分类列表
		List<String> categoryList = searchCategoryList(searchMap);
		map.put("categoryList", categoryList);
		// 3.查询品牌和规格列表
		if (categoryList.size() > 0) {
			map.putAll(searchBrandAndSpecList(categoryList.get(0)));
		}
		return map;
	}

	/**
	 * 根据关键字搜索列表
	 * 
	 * @param keywords
	 * @return
	 */
	public Map<String, Object> searchList(Map searchMap) {
		Map<String, Object> map = new HashMap<>();
		// 1.按关键字查询（高亮显示）
		HighlightQuery query = new SimpleHighlightQuery();
		HighlightOptions options = new HighlightOptions().addField("item_title");// 设置高亮的域
		options.setSimplePrefix("<em style='color:red'>");// 高亮前缀
		options.setSimplePostfix("</em>");// 高亮后缀
		query.setHighlightOptions(options);// 设置高亮选项
		// 添加查询条件
		Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
		query.addCriteria(criteria);
		HighlightPage<Item> queryForPage = solrTemplate.queryForHighlightPage(query, Item.class);
		for (HighlightEntry<Item> h : queryForPage.getHighlighted()) {// 循环高亮入口集合
			Item item = h.getEntity();// 获取原实体类
			if (h.getHighlights().size() > 0 && h.getHighlights().get(0).getSnipplets().size() > 0) {
				item.setTitle(h.getHighlights().get(0).getSnipplets().get(0));// 设置高亮的结果
			}
		}
		map.put("rows", queryForPage.getContent());
		// 2.根据关键字查询商品分类
		List categoryList = searchCategoryList(searchMap);
		map.put("categoryList", categoryList);
		return map;
	}

	/**
	 * 查询分类列表
	 * 
	 * @param searchMap
	 * @return
	 */
	private List searchCategoryList(Map searchMap) {
		List<String> list = new ArrayList();
		Query query = new SimpleQuery();
		// 按照关键字查询
		Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
		query.addCriteria(criteria);
		// 设置分组选项
		GroupOptions groupOptions = new GroupOptions().addGroupByField("item_category");
		query.setGroupOptions(groupOptions);
		// 得到分组页
		GroupPage<Item> page = solrTemplate.queryForGroupPage(query, Item.class);
		// 根据列得到分组结果集
		GroupResult<Item> groupResult = page.getGroupResult("item_category");
		// 得到分组结果入口页
		Page<GroupEntry<Item>> groupEntries = groupResult.getGroupEntries();
		// 得到分组入口集合
		List<GroupEntry<Item>> content = groupEntries.getContent();
		for (GroupEntry<Item> entry : content) {
			list.add(entry.getGroupValue());// 将分组结果的名称封装到返回值中
		}
		return list;
	}

	/**
	 * 查询品牌和规格列表
	 * 
	 * @param category
	 *            分类名称
	 * @return
	 */
	private Map searchBrandAndSpecList(String category) {
		Map map = new HashMap();
		Long typeId = (Long) redisTemplate.boundHashOps("itemCat").get(category);// 获取模板ID
		if (typeId != null) {
			// 根据模板ID查询品牌列表
			List brandList = (List) redisTemplate.boundHashOps("brandList").get(typeId);
			map.put("brandList", brandList);// 返回值添加品牌列表
			// 根据模板ID查询规格列表
			List specList = (List) redisTemplate.boundHashOps("specList").get(typeId);
			map.put("specList", specList);
		}
		return map;
	}

	@Override
	public void importList(List list) {
		solrTemplate.saveBean(list);
		solrTemplate.commit();
	}

	@Override
	public void deleteByGoodsIds(List goodsIds) {
		Query query = new SimpleQuery("*");
		Criteria criteria = new Criteria("item_goodsid").in(goodsIds);
		query.addCriteria(criteria);
		solrTemplate.delete(query);
		solrTemplate.commit();
	}

}
