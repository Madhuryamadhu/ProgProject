package com.madhudevs.javaprogproj.hibernateBeans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="comp_details")
public class ComplexityBean {

	@Id
	@Column(name="COMP_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int complexityId;
	
	@Column(name="COMP_NAME")
	private String complexityName;
	
	public int getComplexityId() {
		return complexityId;
	}
	public void setComplexityId(int complexityId) {
		this.complexityId = complexityId;
	}
	public String getComplexityName() {
		return complexityName;
	}
	public void setComplexityName(String complexityName) {
		this.complexityName = complexityName;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ComplexityBean [complexityId=");
		builder.append(complexityId);
		builder.append(", ");
		if (complexityName != null) {
			builder.append("complexityName=");
			builder.append(complexityName);
		}
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
