package com.mountain.model.dto;

import java.sql.Timestamp;

public class MountainComplete {

	private int userSerial;
	private int mountainSerial;
	private String mountainName;
	private Timestamp commentRegDate;
	private int difficulty;
	private String state;

	public MountainComplete(int userSerial, int mountainSerial, String mountainName, Timestamp commentRegDate,
			int difficulty, String state) {
		this.userSerial = userSerial;
		this.mountainSerial = mountainSerial;
		this.mountainName = mountainName;
		this.commentRegDate = commentRegDate;
		this.difficulty = difficulty;
		this.state = state;
	}

	public int getUserSerial() {
		return userSerial;
	}

	public void setUserSerial(int userSerial) {
		this.userSerial = userSerial;
	}

	public int getMountainSerial() {
		return mountainSerial;
	}

	public void setMountainSerial(int mountainSerial) {
		this.mountainSerial = mountainSerial;
	}

	public String getMountainName() {
		return mountainName;
	}

	public void setMountainName(String mountainName) {
		this.mountainName = mountainName;
	}

	public Timestamp getCommentRegDate() {
		return commentRegDate;
	}

	public void setCommentRegDate(Timestamp commentRegDate) {
		this.commentRegDate = commentRegDate;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "MountainComplete [userSerial=" + userSerial + ", mountainSerial=" + mountainSerial + ", mountainName="
				+ mountainName + ", commentRegDate=" + commentRegDate + ", difficulty=" + difficulty + ", state="
				+ state + "]";
	}

}
