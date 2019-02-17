package com.sc.pojo;

import java.io.Serializable;

/**
 * <p>
 * 内容分类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
public class ContentCategory  implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 类目ID
     */
    private Long id;
    /**
     * 分类名称
     */
    private String name;


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


    @Override
    public String toString() {
        return "ContentCategory{" +
        ", id=" + id +
        ", name=" + name +
        "}";
    }
}
