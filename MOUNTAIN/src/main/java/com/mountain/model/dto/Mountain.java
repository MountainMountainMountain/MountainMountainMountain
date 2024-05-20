package com.mountain.model.dto;

import java.sql.Timestamp;

public class Mountain {
	private int serial;
	private String name;
	private float latitude;
	private float longitude;
	private int altitude;
	private int course;
	private float difficulty;
	private Timestamp regDate;
	private int fee;
	private String state;
	private String town;
	private int point;
	private int weatherNX;
	private int weatherNY;

	public Mountain() {
	}

	public Mountain(int serial, String name, float latitude, float longitude, int altitude, int course,
			float difficulty, Timestamp regDate, int fee, String state, String town, int point, int weatherNX,
			int weatherNY) {
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
		this.point = point;
		this.weatherNX = weatherNX;
		this.weatherNY = weatherNY;
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

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
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

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getWeatherNX() {
		return weatherNX;
	}

	public void setWeatherNX(int weatherNX) {
		this.weatherNX = weatherNX;
	}

	public int getWeatherNY() {
		return weatherNY;
	}

	public void setWeatherNY(int weatherNY) {
		this.weatherNY = weatherNY;
	}

	@Override
	public String toString() {
		return "Mountain [serial=" + serial + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", altitude=" + altitude + ", course=" + course + ", difficulty=" + difficulty + ", regDate="
				+ regDate + ", fee=" + fee + ", state=" + state + ", town=" + town + ", point=" + point + ", weatherNX="
				+ weatherNX + ", weatherNY=" + weatherNY + "]";
	}

}
