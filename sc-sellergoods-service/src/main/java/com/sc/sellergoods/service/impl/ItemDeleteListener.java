package com.sc.sellergoods.service.impl;

import java.util.Arrays;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sc.sellergoods.service.ItemSearchService;

@Component
public class ItemDeleteListener implements MessageListener{

	@Autowired
	private ItemSearchService itemSearchService;
	
	@Override
	public void onMessage(Message message) {
		try {
			ObjectMessage objectMessage = (ObjectMessage) message;
			Long[] goodsIds = (Long[]) objectMessage.getObject();
			System.err.println("ItemDeleteListener监听接收到消息..."+goodsIds);
			itemSearchService.deleteByGoodsIds(Arrays.asList(goodsIds));			
			System.out.println("成功删除索引库中的记录");						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
