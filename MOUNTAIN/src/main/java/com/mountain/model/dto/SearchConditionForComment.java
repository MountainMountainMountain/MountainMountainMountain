package com.mountain.model.dto;

public class SearchConditionForComment {
	private String key;
	private String word;
	private String orderBy;
	private String orderByDir;
	private int mountainSerial;
	// 주석처리와 같이 하려면 mapper의 if문 안에 'none'으로 바꿔야함
//	private String orderBy = "none";
//	private String orderByDir = "none";

	public SearchConditionForComment() {
//		this.orderBy = "none";
//		this.orderByDir = "none";
	}

	public SearchConditionForComment(String key, String word, int mountainSerial) {
		this.key = key;
		this.word = word;
		this.mountainSerial = mountainSerial;
	}

	public SearchConditionForComment(String key, String word, String orderBy, String orderByDir, int mountainSerial) {
		this.key = key;
		this.word = word;
		this.orderBy = orderBy;
		this.orderByDir = orderByDir;
		this.mountainSerial = mountainSerial;
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

	public int getMountainSerial() {
		return mountainSerial;
	}

	public void setMountainSerial(int mountainSerial) {
		this.mountainSerial = mountainSerial;
	}

	@Override
	public String toString() {
		return "SearchConditionForComment [key=" + key + ", word=" + word + ", orderBy=" + orderBy + ", orderByDir="
				+ orderByDir + ", mountainSerial=" + mountainSerial + "]";
	}

}
