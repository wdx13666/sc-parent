package com.sc.mapper;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class a {

	
	public static void main(String[] args) {
		// 全局配置
				GlobalConfig config = new GlobalConfig();
				config.setActiveRecord(true) // 是否支持AR模式
						.setAuthor("wdx") // 作者
						.setOutputDir("E:\\software\\two\\eclipse\\workspace\\stu\\src\\main\\java") // 生成路径
						.setFileOverride(true)// 文件覆盖
						.setServiceName("%sService") // 设置生成的service接口名
						.setIdType(IdType.AUTO) // 主键策略
						.setEnableCache(false);// XML 二级缓存
				;
				// 数据源配置
				DataSourceConfig dsConfig = new DataSourceConfig();
				dsConfig.setDbType(DbType.MYSQL).setUrl("jdbc:mysql://localhost:3306/pinyougoudb").setDriverName("com.mysql.jdbc.Driver")
						.setUsername("root").setPassword("root");
				// 策略配置
				StrategyConfig stConfig = new StrategyConfig();
				stConfig.setCapitalMode(true) // 全局大写命名
						.setDbColumnUnderline(true) // 表名 字段名 是否使用下滑线命名
						.setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
						.setInclude(new String [] {"tb_address","tb_areas","tb_brand","tb_cities","tb_content",
								"tb_content_category","tb_freight_template","tb_goods","tb_goods_desc","tb_item",
								"tb_item_cat","tb_order","tb_order_item","tb_pay_log","tb_provinces","tb_seckill_goods",
								"tb_seckill_order","tb_seller","tb_specification","tb_specification_option","tb_type_template","tb_user"}) // 生成的表
						.setTablePrefix("tb_"); // 表前缀
				// 包名策略
				PackageConfig pkConfig = new PackageConfig();
				pkConfig.setParent("com.sc").setController("manager.controller").setEntity("pojo").setService("sellergoods.service").setXml("mapper");
				AutoGenerator ag = new AutoGenerator().setGlobalConfig(config).setDataSource(dsConfig).setStrategy(stConfig)
						.setPackageInfo(pkConfig);
				ag.execute();
	}
}
