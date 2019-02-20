package com.sc.entity;

import java.io.Serializable;
import java.util.List;

import com.sc.pojo.Goods;
import com.sc.pojo.GoodsDesc;
import com.sc.pojo.Item;

public class GoodsZ implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Goods goods; //商品spu
	private GoodsDesc desc; //商品扩展
	private List<Item> itemList;//商品sku列表
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public GoodsDesc getDesc() {
		return desc;
	}
	public void setDesc(GoodsDesc desc) {
		this.desc = desc;
	}
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	@Override
	public String toString() {
		return "GoodsZ [goods=" + goods + ", desc=" + desc + ", itemList=" + itemList + "]";
	}
	
	
}
