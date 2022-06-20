package com.zr.util;

public class PageUtil {
	
	//当前页码
	private Integer pageNum;
	
	//每页多少行数据
	private Integer pageSize;
	
	//共多少条数据
	private Integer totalCount;
	
	//共多少页
	private Integer totalPage;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPage() {
		if(totalCount % pageSize == 0) {
			totalPage = totalCount/pageSize;
		}else {
			totalPage = totalCount/pageSize + 1;
		}
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
}
