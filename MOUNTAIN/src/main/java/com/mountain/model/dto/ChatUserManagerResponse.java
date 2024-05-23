package com.mountain.model.dto;

import java.sql.Timestamp;

public class ChatUserManagerResponse {
	private int serial;
	private int mountainSerial;
	private Timestamp hikingDate;
	private String mountainName;
	private int course;
	private float difficulty;
	private int fee;
	private String state;
	private String town;
	private int mountainPoint;
	private int userSerial;
	private String name;
	private String gender;
	private String email;
	private int userPoint;

	public ChatUserManagerResponse(int serial, int mountainSerial, Timestamp hikingDate, String mountainName,
			int course, float difficulty, int fee, String state, String town, int mountainPoint, int userSerial,
			String name, String gender, String email, int userPoint) {
		this.serial = serial;
		this.mountainSerial = mountainSerial;
		this.hikingDate = hikingDate;
		this.mountainName = mountainName;
		this.course = course;
		this.difficulty = difficulty;
		this.fee = fee;
		this.state = state;
		this.town = town;
		this.mountainPoint = mountainPoint;
		this.userSerial = userSerial;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.userPoint = userPoint;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public int getMountainSerial() {
		return mountainSerial;
	}

	public void setMountainSerial(int mountainSerial) {
		this.mountainSerial = mountainSerial;
	}

	public Timestamp getHikingDate() {
		return hikingDate;
	}

	public void setHikingDate(Timestamp hikingDate) {
		this.hikingDate = hikingDate;
	}

	public String getMountainName() {
		return mountainName;
	}

	public void setMountainName(String mountainName) {
		this.mountainName = mountainName;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public float getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(float difficulty) {
		this.difficulty = difficulty;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public int getMountainPoint() {
		return mountainPoint;
	}

	public void setMountainPoint(int mountainPoint) {
		this.mountainPoint = mountainPoint;
	}

	public int getUserSerial() {
		return userSerial;
	}

	public void setUserSerial(int userSerial) {
		this.userSerial = userSerial;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}

	@Override
	public String toString() {
		return "ChatUserManagerResponse [serial=" + serial + ", mountainSerial=" + mountainSerial + ", hikingDate="
				+ hikingDate + ", mountainName=" + mountainName + ", course=" + course + ", difficulty=" + difficulty
				+ ", fee=" + fee + ", state=" + state + ", town=" + town + ", mountainPoint=" + mountainPoint
				+ ", userSerial=" + userSerial + ", name=" + name + ", gender=" + gender + ", email=" + email
				+ ", userPoint=" + userPoint + "]";
	}

}
