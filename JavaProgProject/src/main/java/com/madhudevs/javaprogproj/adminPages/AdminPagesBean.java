package com.madhudevs.javaprogproj.adminPages;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AdminPagesBean {

	
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
		}
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
}
