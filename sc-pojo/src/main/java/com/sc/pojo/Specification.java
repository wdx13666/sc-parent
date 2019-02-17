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
public class Specification  implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 名称
     */
    private String specName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }


    @Override
    public String toString() {
        return "Specification{" +
        ", id=" + id +
        ", specName=" + specName +
        "}";
    }
}
