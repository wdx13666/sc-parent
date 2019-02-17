package com.sc.pojo;

import java.io.Serializable;

/**
 * <p>
 * 省份信息表
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
public class Provinces implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 唯一ID
     */
    private Integer id;
    /**
     * 省份ID
     */
    private String provinceid;
    /**
     * 省份名称
     */
    private String province;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }


    @Override
    public String toString() {
        return "Provinces{" +
        ", id=" + id +
        ", provinceid=" + provinceid +
        ", province=" + province +
        "}";
    }
}
