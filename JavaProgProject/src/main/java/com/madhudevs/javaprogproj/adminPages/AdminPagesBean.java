package com.madhudevs.javaprogproj.adminPages;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AdminPagesBean {

	private String username;
	private String password;
	private String pagePath;
	private HashMap<String, String> parameters;
	
	
	private int status;
	private String pageId;    //0- userPages   ||   1-adminPages
	private int perPageCount;
	private int pageNumber;
	private int totalCountofPages;
	private int type;
	private int complexity;
	private int progId;
	private String progName;
	private int isHidden;
	private List<LinkedList<String>> programList;
	private String prog1;
	private String output1;
	
	private List<LinkedList<String>> typeList;
	private String typeName;
	private int adminLevel;
	private int adminId;
	private int adminIsActive;
	
	
	public int getAdminIsActive() {
		return adminIsActive;
	}
	public void setAdminIsActive(int adminIsActive) {
		this.adminIsActive = adminIsActive;
	}
	private List<LinkedList<String>> adminsList;
	
	public List<LinkedList<String>> getAdminsList() {
		return adminsList;
	}
	public void setAdminsList(List<LinkedList<String>> adminsList) {
		this.adminsList = adminsList;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public int getAdminLevel() {
		return adminLevel;
	}
	public void setAdminLevel(int adminLevel) {
		this.adminLevel = adminLevel;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public List<LinkedList<String>> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<LinkedList<String>> typeList) {
		this.typeList = typeList;
	}
	public int getIsHidden() {
		return isHidden;
	}
	public void setIsHidden(int isHidden) {
		this.isHidden = isHidden;
	}
	public String getProg1() {
		return prog1;
	}
	public void setProg1(String prog1) {
		this.prog1 = prog1;
	}
	public String getOutput1() {
		return output1;
	}
	public void setOutput1(String output1) {
		this.output1 = output1;
	}
	public String getPagePath() {
		return pagePath;
	}
	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}
	public HashMap<String, String> getParameters() {
		return parameters;
	}
	public void setParameters(HashMap<String, String> parameters) {
		this.parameters = parameters;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public int getPerPageCount() {
		return perPageCount;
	}
	public void setPerPageCount(int perPageCount) {
		this.perPageCount = perPageCount;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getTotalCountofPages() {
		return totalCountofPages;
	}
	public void setTotalCountofPages(int totalCountofPages) {
		this.totalCountofPages = totalCountofPages;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getComplexity() {
		return complexity;
	}
	public void setComplexity(int complexity) {
		this.complexity = complexity;
	}
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
	public List<LinkedList<String>> getProgramList() {
		return programList;
	}
	public void setProgramList(List<LinkedList<String>> programsList) {
		this.programList = programsList;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AdminPagesBean [");
		if (username != null) {
			builder.append("username=");
			builder.append(username);
			builder.append(", ");
		}
		if (password != null) {
			builder.append("password=");
			builder.append(password);
			builder.append(", ");
		}
		if (pagePath != null) {
			builder.append("pagePath=");
			builder.append(pagePath);
			builder.append(", ");
		}
		if (parameters != null) {
			builder.append("parameters=");
			builder.append(parameters);
			builder.append(", ");
		}
		builder.append("status=");
		builder.append(status);
		builder.append(", ");
		if (pageId != null) {
			builder.append("pageId=");
			builder.append(pageId);
			builder.append(", ");
		}
		builder.append("perPageCount=");
		builder.append(perPageCount);
		builder.append(", pageNumber=");
		builder.append(pageNumber);
		builder.append(", adminIsActive=");
		builder.append(adminIsActive);
		builder.append(", totalCountofPages=");
		builder.append(totalCountofPages);
		builder.append(", type=");
		builder.append(type);
		builder.append(", complexity=");
		builder.append(complexity);
		builder.append(", progId=");
		builder.append(progId);
		builder.append(", ");
		if (progName != null) {
			builder.append("progName=");
			builder.append(progName);
			builder.append(", ");
		}
		builder.append("isHidden=");
		builder.append(isHidden);
		builder.append(", ");
		if (programList != null) {
			builder.append("programList=");
			builder.append(programList);
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
		if (typeList != null) {
			builder.append("typeList=");
			builder.append(typeList);
			builder.append(", ");
		}
		if (typeName != null) {
			builder.append("typeName=");
			builder.append(typeName);
			builder.append(", ");
		}
		builder.append("adminLevel=");
		builder.append(adminLevel);
		builder.append(", adminId=");
		builder.append(adminId);
		builder.append(", ");
		if (adminsList != null) {
			builder.append("adminsList=");
			builder.append(adminsList);
		}
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
	
}
