package com.sc.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.sc.pojo.Item;
import com.sc.sellergoods.service.ItemSearchService;

@Component
public class ItemSearchListener implements MessageListener{

	@Autowired
	private ItemSearchService itemSearchService;
	
	@Override
	public void onMessage(Message message) {

		System.err.println("监听接收到的消息.............");
		try {
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			List<Item> list = JSON.parseArray(text,Item.class);
			for (Item item : list) {
				System.out.println(item.getId()+" "+item.getTitle());	
				Map specMap= JSON.parseObject(item.getSpec());//将spec字段中的json字符串转换为map
				item.setSpecMap(specMap);//给带注解的字段赋值
			}
			itemSearchService.importList(list);//导入	
			System.out.println("成功导入到索引库");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}