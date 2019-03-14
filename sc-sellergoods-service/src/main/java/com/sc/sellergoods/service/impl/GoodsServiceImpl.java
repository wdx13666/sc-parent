package com.sc.sellergoods.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sc.entity.GoodsZ;
import com.sc.mapper.BrandMapper;
import com.sc.mapper.GoodsDescMapper;
import com.sc.mapper.GoodsMapper;
import com.sc.mapper.ItemCatMapper;
import com.sc.mapper.ItemMapper;
import com.sc.mapper.SellerMapper;
import com.sc.pojo.Brand;
import com.sc.pojo.Goods;
import com.sc.pojo.Item;
import com.sc.pojo.ItemCat;
import com.sc.pojo.Seller;
import com.sc.sellergoods.service.GoodsService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
@Service
@Transactional
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

	@Autowired
	private GoodsDescMapper descMapper;
	@Autowired
	private BrandMapper brandMapper;
	@Autowired
	private ItemCatMapper itemCatMapper;
	@Autowired
	private SellerMapper sellerMapper;
	@Autowired
	private ItemMapper itemMapper;
	
	@Override
	public void add(GoodsZ goodsZ){
		goodsZ.getGoods().setAuditStatus("0");//设置未申请状态
		baseMapper.add(goodsZ.getGoods());//插入商品表
		System.err.println("goodsId="+goodsZ.getGoods().getId());
		goodsZ.getDesc().setGoodsId(goodsZ.getGoods().getId());//设置ID
		descMapper.insert(goodsZ.getDesc());//插入商品扩展数据
		if("1".equals(goodsZ.getGoods().getIsEnableSpec())){
		for (Item item : goodsZ.getItemList()) {
			//标题
			String title = goodsZ.getGoods().getGoodsName();
			Map<String,Object> specMap = JSON.parseObject(item.getSpec());
			for(String key:specMap.keySet()){
				title+=" "+ specMap.get(key);
			}
			item.setTitle(title);		
			setItemValus(goodsZ,item);
			itemMapper.insert(item);
			}
		}else{
			Item item=new Item();
			item.setTitle(goodsZ.getGoods().getGoodsName());//商品KPU+规格描述串作为SKU名称
			item.setPrice( goodsZ.getGoods().getPrice() );//价格			
			item.setStatus("1");//状态
			item.setIsDefault("1");//是否默认			
			item.setNum(99999);//库存数量
			item.setSpec("{}");			
			setItemValus(goodsZ,item);					
			itemMapper.insert(item);
			}	
		
	}
	
	
	private void setItemValus(GoodsZ goodsZ,Item item) {
		item.setGoodsId(goodsZ.getGoods().getId());//商品SPU编号
		item.setSellerId(goodsZ.getGoods().getSellerId());//商家编号
		item.setCategoryid(goodsZ.getGoods().getCategory3Id());//商品分类编号（3级）
		item.setCreateTime(new Date());//创建日期
		item.setUpdateTime(new Date());//修改日期 
		
		//品牌名称
		Brand brand = brandMapper.selectById(goodsZ.getGoods().getBrandId());
		item.setBrand(brand.getName());
		//分类名称
		ItemCat itemCat = itemCatMapper.selectById(goodsZ.getGoods().getCategory3Id());
		item.setCategory(itemCat.getName());
		
		//商家名称
		Seller seller = sellerMapper.selectById(goodsZ.getGoods().getSellerId());
		item.setSeller(seller.getNickName());
		
		//图片地址（取spu的第一个图片）
		List<Map> imageList = JSON.parseArray(goodsZ.getDesc().getItemImages(), Map.class) ;
		if(imageList.size()>0){
			item.setImage ( (String)imageList.get(0).get("url"));
		}	
	}
	
}
