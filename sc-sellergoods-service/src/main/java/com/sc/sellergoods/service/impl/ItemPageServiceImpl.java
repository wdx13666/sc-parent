package com.sc.sellergoods.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sc.mapper.GoodsDescMapper;
import com.sc.mapper.GoodsMapper;
import com.sc.mapper.ItemCatMapper;
import com.sc.mapper.ItemMapper;
import com.sc.pojo.Goods;
import com.sc.pojo.GoodsDesc;
import com.sc.pojo.Item;
import com.sc.sellergoods.service.ItemPageService;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
@Service
public class ItemPageServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemPageService {

	@Value("${pagedir}")
	private String pagedir;
	@Autowired
	private FreeMarkerConfigurer configurer;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsDescMapper descMapper;
	@Autowired
	private ItemCatMapper itemCatMapper;
	@Autowired
	private ItemMapper itemMapper;

	@Override
	public boolean getItemHtml(Long goodsId) {

		try {
			Configuration configuration = configurer.getConfiguration();
			Template template = configuration.getTemplate("item.ftl");
			Map map = new HashMap<>();
			// 1.加载商品表数据
			Goods goods = goodsMapper.selectById(goodsId);
			map.put("goods", goods);
			// 2.加载商品扩展表数据
			GoodsDesc goodsDesc = descMapper.selectById(goodsId);
			map.put("goodsDesc", goodsDesc);
			// 3.商品分类
			String itemCat1 = itemCatMapper.selectById(goods.getCategory1Id()).getName();
			String itemCat2 = itemCatMapper.selectById(goods.getCategory2Id()).getName();
			String itemCat3 = itemCatMapper.selectById(goods.getCategory3Id()).getName();
			map.put("itemCat1", itemCat1);
			map.put("itemCat2", itemCat2);
			map.put("itemCat3", itemCat3);
			// 4.读取SKU列表
			List<Item> itemList = itemMapper.selectList(
					new EntityWrapper<Item>().eq("goods_id", goodsId).eq("status", 1).orderBy("is_default", false));
			map.put("itemList", itemList);
			Writer out = new FileWriter(pagedir + goodsId + ".html");
			template.process(map, out);
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteItemHtml(Long[] goodsIds) {
		try {
			for(Long goodsId:goodsIds){
				new File(pagedir+goodsId+".html").delete();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}

}
