package com.sagar.model;

public class ProductSearchRequest {
	
    private int userId;
    private String bookSearch;
    private String sortBy="id";
    private String sortOrder="asc";
    private int recordsPerPage=10;
    private int pageIndex=1;
    
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getBookSearch() {
		return bookSearch;
	}
	public void setBookSearch(String bookSearch) {
		this.bookSearch = bookSearch;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public int getRecordsPerPage() {
		return recordsPerPage;
	}
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
    
}
