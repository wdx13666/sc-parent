package com.sc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sc.pojo.TbProvinces;
import com.sc.pojo.TbProvincesExample;

public interface TbProvincesMapper {
    int countByExample(TbProvincesExample example);

    int deleteByExample(TbProvincesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbProvinces record);

    int insertSelective(TbProvinces record);

    List<TbProvinces> selectByExample(TbProvincesExample example);

    TbProvinces selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbProvinces record, @Param("example") TbProvincesExample example);

    int updateByExample(@Param("record") TbProvinces record, @Param("example") TbProvincesExample example);

    int updateByPrimaryKeySelective(TbProvinces record);

    int updateByPrimaryKey(TbProvinces record);
}