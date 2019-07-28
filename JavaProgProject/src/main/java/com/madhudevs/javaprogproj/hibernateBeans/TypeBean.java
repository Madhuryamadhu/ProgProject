package com.madhudevs.javaprogproj.hibernateBeans;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="type_details")
public class TypeBean {

	@Id
	@Column(name="TYPE_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int typeId;
	
	@Column(name="TYPE_NAME")
	private String typeName;
	
	@Column(name="IS_HIDDEN")
	private int isHidden;
	
	@Column(name="CREATE_USER")
	private String createUser;
	
	@Column(name="CREATE_DATE")
	private Date createDate;
	
	@Column(name="MODIFY_USER")
	private String modifyUser;
	
	@Column(name="MODIFY_DATE")
	private String modifyDate;
	
	
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getIsHidden() {
		return isHidden;
	}
	public void setIsHidden(int isHidden) {
		this.isHidden = isHidden;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TypeBean [typeId=");
		builder.append(typeId);
		builder.append(", ");
		if (typeName != null) {
			builder.append("typeName=");
			builder.append(typeName);
			builder.append(", ");
		}
		builder.append("isHidden=");
		builder.append(isHidden);
		builder.append(", ");
		if (createUser != null) {
			builder.append("createUser=");
			builder.append(createUser);
			builder.append(", ");
		}
		if (createDate != null) {
			builder.append("createDate=");
			builder.append(createDate);
			builder.append(", ");
		}
		if (modifyUser != null) {
			builder.append("modifyUser=");
			builder.append(modifyUser);
			builder.append(", ");
		}
		if (modifyDate != null) {
			builder.append("modifyDate=");
			builder.append(modifyDate);
		}
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
