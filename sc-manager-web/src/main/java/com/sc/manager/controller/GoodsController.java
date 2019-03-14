package com.sc.manager.controller;

import java.util.Arrays;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sc.entity.GoodsZ;
import com.sc.entity.PageResult;
import com.sc.entity.Result;
import com.sc.pojo.Goods;
import com.sc.pojo.GoodsDesc;
import com.sc.pojo.Item;
import com.sc.sellergoods.service.GoodsDescService;
import com.sc.sellergoods.service.GoodsService;
import com.sc.sellergoods.service.ItemPageService;
import com.sc.sellergoods.service.ItemSearchService;
import com.sc.sellergoods.service.ItemService;

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
	@Reference
	private ItemPageService itemPageService;
	@Reference
	private ItemService itemService;
/*	@Reference
	private ItemSearchService itemSearchService;*/
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

	@Autowired
	private Destination queneSolrDeleteDestination;
	@Autowired
	private Destination topicPageDeleteDestination;//用于删除静态网页的消息
	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(final Long[] ids) {
		try {
			goodsService.deleteBatchIds(Arrays.asList(ids));
			jmsTemplate.send(queneSolrDeleteDestination, new MessageCreator() {
				
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createObjectMessage(ids);
				}
			});
			//删除生成的静态页面
			jmsTemplate.send(topicPageDeleteDestination,new MessageCreator() {
				
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createObjectMessage(ids);
				}
			});
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

	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Destination queueSolrDestination;//用于发送solr导入的消息
	@Autowired
	private Destination topicPageDestination;//用于发送solr导入的消息
	
	@RequestMapping("/updateStatus")
	public Result updateStatus(Long [] ids,String status){
		try {
			
			try {
				List<Goods> batchIds = goodsService.selectBatchIds(Arrays.asList(ids));
				goodsService.updateBatchById(batchIds);			
				//按照SPU ID查询 SKU列表(状态为1)		
				if(status.equals("1")){//审核通过
					List<Item> itemList = itemService.selectList(new EntityWrapper<Item>().in("id", ids).eq("status",status));
					//调用搜索接口实现数据批量导入
					if(itemList.size()>0){				
						/*itemSearchService.importList(itemList);*/		
					final String jsonString = JSON.toJSONString(itemList);
					jmsTemplate.send(queueSolrDestination, new MessageCreator() {
						
						@Override
						public Message createMessage(Session session) throws JMSException {
							return session.createTextMessage(jsonString);
						}
					});
					}else{
						System.out.println("没有明细数据");
					}				
					//静态页生成
					for(final Long goodsId:ids){
//						itemPageService.getItemHtml(goodsId);
						jmsTemplate.send(topicPageDestination,new MessageCreator() {
							
							@Override
							public Message createMessage(Session session) throws JMSException {
								return session.createTextMessage(goodsId + "");
							}
						});
					}				
				}					
				return new Result(true, "修改状态成功"); 
			} catch (Exception e) {
				e.printStackTrace();
				return new Result(false, "修改状态失败");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return  new Result(false, "失败 ");

		}
	}

	/**
	 * 生成静态页
	 * 
	 * @param goodsId
	 */
	@RequestMapping("/getHtml")
	public void getItemHtml(Long goodsId) {
		itemPageService.getItemHtml(goodsId);
	}

}
