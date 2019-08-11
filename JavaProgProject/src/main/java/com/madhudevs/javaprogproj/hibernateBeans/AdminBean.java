package com.madhudevs.javaprogproj.hibernateBeans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin_details")
public class AdminBean {

	@Id
	@Column(name="ADMIN_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int adminId;
	
	@Column(name="ADMIN_USERNAME")
	private String adminUsername;
	
	@Column(name="ADMIN_PASSWORD")
	private String adminPassword;
	
	@Column(name="CREATE_DATE")
	private Date createdate;
	
	@Column(name="CREATE_USER")
	private int createUser;
	
	@Column(name="IS_ACTIVE")
	private int isActive;
	
	@Column(name="LEVEL")
	private int level;
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminUsername() {
		return adminUsername;
	}
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public int getCreateUser() {
		return createUser;
	}
	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AdminBean [adminId=");
		builder.append(adminId);
		builder.append(", ");
		if (adminUsername != null) {
			builder.append("adminUsername=");
			builder.append(adminUsername);
			builder.append(", ");
		}
		if (adminPassword != null) {
			builder.append("adminPassword=");
			builder.append(adminPassword);
			builder.append(", ");
		}
		if (createdate != null) {
			builder.append("createdate=");
			builder.append(createdate);
			builder.append(", ");
		}
		
		builder.append("isActive=");
		builder.append(isActive);
		builder.append(", level=");
		builder.append(level);
		builder.append(", createUser=");
		builder.append(createUser);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
