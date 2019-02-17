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
public class Brand  implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 品牌名称
     */
    private String name;
    /**
     * 品牌首字母
     */
    private String firstChar;


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

    public String getFirstChar() {
        return firstChar;
    }

    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar;
    }


    @Override
    public String toString() {
        return "Brand{" +
        ", id=" + id +
        ", name=" + name +
        ", firstChar=" + firstChar +
        "}";
    }
}
