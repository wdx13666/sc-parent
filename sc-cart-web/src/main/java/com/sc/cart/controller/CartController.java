package com.sc.cart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.sc.entity.Cart;
import com.sc.entity.Result;
import com.sc.sellergoods.service.CartService;
import com.sc.utils.CookieUtil;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Reference
	private CartService cartService;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	/**
	 * 购物车列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/findCartList")
	public List<Cart> findCartList() {
		String username = "wdx";

		// 读取本地购物车//
		String cookieValue = CookieUtil.getCookieValue(request, "cartList", "utf-8");
		if (cookieValue == null || cookieValue.equals("")) {
			cookieValue = "[]";

		}
		List<Cart> cartList_cookie = JSON.parseArray(cookieValue, Cart.class);
		if (!username.equals("wdx")) {// 如果未登录
			return cartList_cookie;
		} else {
			List<Cart> cartList_redis = cartService.findCartListFromRedis(username);// 从redis中提取
			if (cartList_cookie.size() > 0) {// 如果本地存在购物车
				// 合并购物车
				cartList_redis = cartService.mergeCartList(cartList_cookie, cartList_cookie);
				//清除本地cookie的数据
				CookieUtil.deleteCookie(request, response, "cartList");
				//将合并后的数据存入redis 
				cartService.saveCartListToRedis(username, cartList_redis); 
			}
			return cartList_redis;
		}
	}

	/**
	 * 添加商品到购物车
	 * 
	 * @param request
	 * @param response
	 * @param itemId
	 * @param num
	 * @return
	 */
	@RequestMapping("/addGoodsToCartList")
	public Result addGoodsToCartList(Long itemId, Integer num) {
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:9105");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		String username = "wdx";
		try {
			List<Cart> cartList = findCartList();// 获取购物车列表
			cartList = cartService.addGoodsToCartList(cartList, itemId, num);
			if (!username.equals("wdx")) {
				CookieUtil.setCookie(request, response, "cartList", JSON.toJSONString(cartList), 3600 * 24, "UTF-8");
			} else {
				cartService.saveCartListToRedis(username, cartList);
			}
			return new Result(true, "添加成功");
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new Result(false, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "添加失败");
		}
	}

}
