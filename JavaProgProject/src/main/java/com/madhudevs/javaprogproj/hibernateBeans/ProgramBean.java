package com.madhudevs.javaprogproj.hibernateBeans;

import java.sql.Blob;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="prog_details")
public class ProgramBean {
	@Id
	@Column(name="PROG_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int progId;
	
	@Column(name="PROG_NAME")
	private String progName;
	
	@Column(name="PROG1")
	private byte[] prog1;
	
	@Column(name="OUTPUT1")
	private byte[] output1;
	
	@Column(name="PROG2")
	private String prog2;
	
	@Column(name="OUTPUT2")
	private String output2;
	
	@Column(name="PROG3")
	private String prog3;
	
	@Column(name="OUTPUT3")
	private String output3;
	
	@Column(name="CREATE_USER")
	private String createUser;
	
	@Column(name="CREATE_DATE")
	private Date createDate;
	
	@Column(name="IS_TESTED")
	private int isTested;
	
	@Column(name="TYPE")
	private int type;
	
	@Column(name="IS_HIDDEN")
	private int isHidden;
	
	@Column(name="MODIFY_USER")
	private String modifyUser;
	
	@Column(name="MODIFY_DATE")
	private Date modifyDate;
	
	@Column(name="MODIFY_COUNT")
	private int modifyCount;
	
	@Column(name="L_COUNT")
	private int lCount;
	
	@Column(name="DL_COUNT")
	private int dlCount;
	
	@Column(name="COMPLEXITY")
	private int complexity;
	
	
	public int getProgId() {
		return progId;
	}
	public void setProgId(int progId) {
		this.progId = progId;
	}
	public String getProgName() {
		return progName;
	}
	public void setProgName(String progName) {
		this.progName = progName;
	}
	
	
	public byte[] getProg1() {
		return prog1;
	}
	public void setProg1(byte[] prog1) {
		this.prog1 = prog1;
	}
	public byte[] getOutput1() {
		return output1;
	}
	public void setOutput1(byte[] output1) {
		this.output1 = output1;
	}
	public String getProg2() {
		return prog2;
	}
	public void setProg2(String prog2) {
		this.prog2 = prog2;
	}
	public String getOutput2() {
		return output2;
	}
	public void setOutput2(String output2) {
		this.output2 = output2;
	}
	public String getProg3() {
		return prog3;
	}
	public void setProg3(String prog3) {
		this.prog3 = prog3;
	}
	public String getOutput3() {
		return output3;
	}
	public void setOutput3(String output3) {
		this.output3 = output3;
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
	public int getIsTested() {
		return isTested;
	}
	public void setIsTested(int isTested) {
		this.isTested = isTested;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getIsHidden() {
		return isHidden;
	}
	public void setIsHidden(int isHidden) {
		this.isHidden = isHidden;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public int getModifyCount() {
		return modifyCount;
	}
	public void setModifyCount(int modifyCount) {
		this.modifyCount = modifyCount;
	}
	public int getlCount() {
		return lCount;
	}
	public void setlCount(int lCount) {
		this.lCount = lCount;
	}
	public int getDlCount() {
		return dlCount;
	}
	public void setDlCount(int dlCount) {
		this.dlCount = dlCount;
	}
	public int getComplexity() {
		return complexity;
	}
	public void setComplexity(int complexity) {
		this.complexity = complexity;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProgramBean [progId=");
		builder.append(progId);
		builder.append(", ");
		if (progName != null) {
			builder.append("progName=");
			builder.append(progName);
			builder.append(", ");
		}
		if (prog1 != null) {
			builder.append("prog1=");
			builder.append(prog1);
			builder.append(", ");
		}
		if (output1 != null) {
			builder.append("output1=");
			builder.append(output1);
			builder.append(", ");
		}
		if (prog2 != null) {
			builder.append("prog2=");
			builder.append(prog2);
			builder.append(", ");
		}
		if (output2 != null) {
			builder.append("output2=");
			builder.append(output2);
			builder.append(", ");
		}
		if (prog3 != null) {
			builder.append("prog3=");
			builder.append(prog3);
			builder.append(", ");
		}
		if (output3 != null) {
			builder.append("output3=");
			builder.append(output3);
			builder.append(", ");
		}
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
		builder.append("isTested=");
		builder.append(isTested);
		builder.append(", type=");
		builder.append(type);
		builder.append(", isHidden=");
		builder.append(isHidden);
		builder.append(", ");
		if (modifyUser != null) {
			builder.append("modifyUser=");
			builder.append(modifyUser);
			builder.append(", ");
		}
		if (modifyDate != null) {
			builder.append("modifyDate=");
			builder.append(modifyDate);
			builder.append(", ");
		}
		builder.append("modifyCount=");
		builder.append(modifyCount);
		builder.append(", lCount=");
		builder.append(lCount);
		builder.append(", dlCount=");
		builder.append(dlCount);
		builder.append(", complexity=");
		builder.append(complexity);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
