package com.madhudevs.javaprogproj.userSection;

import java.util.HashMap;

public class UserPagesBean {

	private int status;
	private String pageId;    //0- userPages   ||   1-adminPages
	private int perPageCount;
	private int pageNumber;
	private int totalCountofPages;
	private int type;
	private int complexity;
	private int progId;
	private String progName;
	private String program1;
	private String output1;
	private int likeCount;
	private int dislikeCount;
	private HashMap<Integer, String> typeMap;
	private HashMap<Integer, String> complexityMap;
	private HashMap<Integer, String> programMap;
	private HashMap<Integer, String> likeCountMap;
	private HashMap<Integer, String> dislikeCountMap;
	
	
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getDislikeCount() {
		return dislikeCount;
	}
	public void setDislikeCount(int dislikeCount) {
		this.dislikeCount = dislikeCount;
	}
	public String getOutput1() {
		return output1;
	}
	public void setOutput1(String output1) {
		this.output1 = output1;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPerPageCount() {
		return perPageCount;
	}
	public void setPerPageCount(int perPageCount) {
		this.perPageCount = perPageCount;
	}
	
	public int getTotalCountofPages() {
		return totalCountofPages;
	}
	public void setTotalCountofPages(int totalCountofPages) {
		this.totalCountofPages = totalCountofPages;
	}
	public HashMap<Integer, String> getLikeCountMap() {
		return likeCountMap;
	}
	public void setLikeCountMap(HashMap<Integer, String> likeCountMap) {
		this.likeCountMap = likeCountMap;
	}
	public HashMap<Integer, String> getDislikeCountMap() {
		return dislikeCountMap;
	}
	public void setDislikeCountMap(HashMap<Integer, String> dislikeCountMap) {
		this.dislikeCountMap = dislikeCountMap;
	}
	public String getProgName() {
		return progName;
	}
	public void setProgName(String progName) {
		this.progName = progName;
	}
	public String getProgram1() {
		return program1;
	}
	public void setProgram1(String program1) {
		this.program1 = program1;
	}
	public int getProgId() {
		return progId;
	}
	public void setProgId(int progId) {
		this.progId = progId;
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
	public HashMap<Integer, String> getProgramMap() {
		return programMap;
	}
	public void setProgramMap(HashMap<Integer, String> programMap) {
		this.programMap = programMap;
	}
	public HashMap<Integer, String> getComplexityMap() {
		return complexityMap;
	}
	public void setComplexityMap(HashMap<Integer, String> complexityMap) {
		this.complexityMap = complexityMap;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public HashMap<Integer, String> getTypeMap() {
		return typeMap;
	}
	public void setTypeMap(HashMap<Integer, String> typeMap) {
		this.typeMap = typeMap;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserPagesBean [status=");
		builder.append(status);
		builder.append(", ");
		if (pageId != null) {
			builder.append("pageId=");
			builder.append(pageId);
			builder.append(", ");
		}
		builder.append("perPageCount=");
		builder.append(perPageCount);
		builder.append(", totalCountofPages=");
		builder.append(totalCountofPages);
		builder.append(", type=");
		builder.append(type);
		builder.append("pageNumber=");
		builder.append(pageNumber);
		builder.append(", complexity=");
		builder.append(complexity);
		builder.append(", progId=");
		builder.append(progId);
		builder.append(", likeCount=");
		builder.append(likeCount);
		builder.append(", dislikeCount=");
		builder.append(dislikeCount);
		builder.append(", ");
		if (progName != null) {
			builder.append("progName=");
			builder.append(progName);
			builder.append(", ");
		}
		if (program1 != null) {
			builder.append("program1=");
			builder.append(program1);
			builder.append(", ");
		}
		if (typeMap != null) {
			builder.append("typeMap=");
			builder.append(typeMap);
			builder.append(", ");
		}
		if (complexityMap != null) {
			builder.append("complexityMap=");
			builder.append(complexityMap);
			builder.append(", ");
		}
		if (programMap != null) {
			builder.append("programMap=");
			builder.append(programMap);
			builder.append(", ");
		}
		if (likeCountMap != null) {
			builder.append("likeCountMap=");
			builder.append(likeCountMap);
			builder.append(", ");
		}
		if (dislikeCountMap != null) {
			builder.append("dislikeCountMap=");
			builder.append(dislikeCountMap);
		}
		if (output1 != null) {
			builder.append("output1=");
			builder.append(output1);
		}
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
	
	
	
	
}
