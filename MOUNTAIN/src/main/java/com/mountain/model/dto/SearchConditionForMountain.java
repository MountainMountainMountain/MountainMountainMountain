package com.mountain.model.dto;

public class SearchConditionForMountain {
	private String key;
	private String word;
	private String orderBy;
	private String orderByDir;
	private String state;
	// 주석처리와 같이 하려면 mapper의 if문 안에 'none'으로 바꿔야함
//	private String orderBy = "none";
//	private String orderByDir = "none";

	public SearchConditionForMountain() {
//		this.orderBy = "none";
//		this.orderByDir = "none";
	}

	public SearchConditionForMountain(String key, String word, String state) {
		this.key = key;
		this.word = word;
		this.state = state;
	}

	public SearchConditionForMountain(String key, String word, String orderBy, String orderByDir, String state) {
		this.key = key;
		this.word = word;
		this.orderBy = orderBy;
		this.orderByDir = orderByDir;
		this.state = state;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderByDir() {
		return orderByDir;
	}

	public void setOrderByDir(String orderByDir) {
		this.orderByDir = orderByDir;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "SearchCondition [key=" + key + ", word=" + word + ", orderBy=" + orderBy + ", orderByDir=" + orderByDir
				+ ", state=" + state + "]";
	}

}
