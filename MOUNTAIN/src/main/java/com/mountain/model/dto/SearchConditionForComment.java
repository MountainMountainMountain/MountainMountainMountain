package com.mountain.model.dto;

public class SearchConditionForComment {
	private String key;
	private String word;
	private String orderBy;
	private String orderByDir;
	private int mountainSerial;

	public SearchConditionForComment() {
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
