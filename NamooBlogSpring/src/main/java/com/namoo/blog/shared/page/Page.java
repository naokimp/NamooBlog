package com.namoo.blog.shared.page;

import java.util.List;

public class Page<T> {
	//
	private PageInfo pageInfo;
	private int totalCount;
	private List<T> results;
	
	//--------------------------------------------------------------------------

	public Page() {
		//
	}
	
	public Page(PageInfo pageInfo) {
		//
		this.pageInfo = pageInfo;
	}
	
	public Page(PageInfo pageInfo, int totalCount) {
		//
		this.pageInfo = pageInfo;
		this.totalCount = totalCount;
	}

	public Page(PageInfo pageInfo, int totalCount, List<T> results) {
		//
		this.pageInfo = pageInfo;
		this.totalCount = totalCount;
		this.results = results;		
	}
	
	//--------------------------------------------------------------------------
	
	public boolean isNextPage() {
		//
		return ((pageInfo.getCountPerPage() * pageInfo.getPageNo()) < totalCount);
	}
	
	//--------------------------------------------------------------------------
	
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getResults() {
		return results;
	}
	public void setResults(List<T> results) {
		this.results = results;
	}

}
