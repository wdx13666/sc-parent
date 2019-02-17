package com.sc.pojo;

import java.io.Serializable;

/**
 * <p>
 * 商品类目
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
public class ItemCat  implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 类目ID
     */
    private Long id;
    /**
     * 父类目ID=0时，代表的是一级的类目
     */
    private Long parentId;
    /**
     * 类目名称
     */
    private String name;
    /**
     * 类型id
     */
    private Long typeId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }


    @Override
    public String toString() {
        return "ItemCat{" +
        ", id=" + id +
        ", parentId=" + parentId +
        ", name=" + name +
        ", typeId=" + typeId +
        "}";
    }
}
