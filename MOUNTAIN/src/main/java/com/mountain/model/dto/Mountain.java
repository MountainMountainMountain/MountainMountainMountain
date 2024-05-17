package com.mountain.model.dto;

import java.sql.Timestamp;

public class Mountain {
	private int serial;
	private String name;
	private int latitude;
	private int longitude;
	private int altitude;
	private int course;
	private float difficulty;
	private Timestamp regDate;
	private int fee;
	private String state;
	private String town;

	public Mountain() {
	}

	public Mountain(int serial, String name, int latitude, int longitude, int altitude, int course, float difficulty,
			Timestamp regDate, int fee, String state, String town) {
		this.serial = serial;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.course = course;
		this.difficulty = difficulty;
		this.regDate = regDate;
		this.fee = fee;
		this.state = state;
		this.town = town;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	public int getAltitude() {
		return altitude;
	}

	public void setAltitude(int altitude) {
		this.altitude = altitude;
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

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
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

	@Override
	public String toString() {
		return "Mountain [serial=" + serial + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", altitude=" + altitude + ", course=" + course + ", difficulty=" + difficulty + ", regDate="
				+ regDate + ", fee=" + fee + ", state=" + state + ", town=" + town + "]";
	}

}
