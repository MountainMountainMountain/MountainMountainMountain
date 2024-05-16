package com.mountain.model.dto;

public class SearchCondition {
	private String key;
	private String word;
	private String orderBy;
	private String orderByDir;
	// 주석처리와 같이 하려면 mapper의 if문 안에 'none'으로 바꿔야함
//	private String orderBy = "none";
//	private String orderByDir = "none";

	public SearchCondition() {
//		this.orderBy = "none";
//		this.orderByDir = "none";
	}

}
