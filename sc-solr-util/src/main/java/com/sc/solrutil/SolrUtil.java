package com.sc.solrutil;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sc.mapper.ItemMapper;
import com.sc.pojo.Item;

@Component
public class SolrUtil {

	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private SolrTemplate solrTemplate;
	
	
	public void importItemData(){
		
		List<Item> itemList = itemMapper.selectList(new EntityWrapper<Item>().eq("status", "1"));
		System.out.println("---商品列表---");
		for(Item item:itemList){
			System.out.println(item.getId()+" "+ item.getTitle()+ " "+item.getPrice());	
			Map specMap = JSON.parseObject(item.getSpec(), Map.class);//从数据库中提取规格json字符串转换为map
			item.setSpecMap(specMap);
		}
		
		solrTemplate.saveBeans(itemList);
		solrTemplate.commit();
		
		System.out.println("---结束---");
	}
	
	public void delete(){
		Query query = new SimpleQuery("*");
		solrTemplate.delete(query);
		solrTemplate.commit();
	}
	
	public static void main(String[] args) {
		
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath*:spring/applicationContext*.xml");
		SolrUtil solrUtil=  (SolrUtil) context.getBean("solrUtil");
		solrUtil.importItemData();
//		solrUtil.delete();
	}
	
	
}
