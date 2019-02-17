package com.sc.pojo;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
public class TypeTemplate  implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 模板名称
     */
    private String name;
    /**
     * 关联规格
     */
    private String specIds;
    /**
     * 关联品牌
     */
    private String brandIds;
    /**
     * 自定义属性
     */
    private String customAttributeItems;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecIds() {
        return specIds;
    }

    public void setSpecIds(String specIds) {
        this.specIds = specIds;
    }

    public String getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(String brandIds) {
        this.brandIds = brandIds;
    }

    public String getCustomAttributeItems() {
        return customAttributeItems;
    }

    public void setCustomAttributeItems(String customAttributeItems) {
        this.customAttributeItems = customAttributeItems;
    }


    @Override
    public String toString() {
        return "TypeTemplate{" +
        ", id=" + id +
        ", name=" + name +
        ", specIds=" + specIds +
        ", brandIds=" + brandIds +
        ", customAttributeItems=" + customAttributeItems +
        "}";
    }
}
