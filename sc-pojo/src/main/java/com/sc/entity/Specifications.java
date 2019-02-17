package com.sc.entity;

import java.io.Serializable;
import java.util.List;

import com.sc.pojo.Specification;
import com.sc.pojo.SpecificationOption;

/**
 * 规格组合实体类 
 * @author Administrator
 *
 */
public class Specifications implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	private Specification specification;
	private List<SpecificationOption> specificationOptionList;
	public Specification getSpecification() {
		return specification;
	}
	public void setSpecification(Specification specification) {
		this.specification = specification;
	}
	public List<SpecificationOption> getSpecificationOptionList() {
		return specificationOptionList;
	}
	public void setSpecificationOptionList(List<SpecificationOption> specificationOptionList) {
		this.specificationOptionList = specificationOptionList;
	}
	@Override
	public String toString() {
		return "Specifications [specification=" + specification + ", specificationOptionList=" + specificationOptionList
				+ "]";
	}
	
	
	
	
	
	
}
