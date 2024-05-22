package com.mountain.model.dto;

import java.sql.Timestamp;

public class ChatLogResponse {
	private long serial;
	private String content;
	private Timestamp createDate;
	private int userSerial;
	private String userName;
	private String userId;
	private int userPoint;
	private String userGender;
	private int mountainSerial;
	private String mountainName;
	private Timestamp hikingDate;

	public ChatLogResponse() {
	}

	public ChatLogResponse(long serial, String content, Timestamp createDate, int userSerial, String userName,
			String userId, int userPoint, String userGender, int mountainSerial, String mountainName,
			Timestamp hikingDate) {
		this.serial = serial;
		this.content = content;
		this.createDate = createDate;
		this.userSerial = userSerial;
		this.userName = userName;
		this.userId = userId;
		this.userPoint = userPoint;
		this.userGender = userGender;
		this.mountainSerial = mountainSerial;
		this.mountainName = mountainName;
		this.hikingDate = hikingDate;
	}

	public long getSerial() {
		return serial;
	}

	public void setSerial(long serial) {
		this.serial = serial;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public int getUserSerial() {
		return userSerial;
	}

	public void setUserSerial(int userSerial) {
		this.userSerial = userSerial;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
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

	public Timestamp getHikingDate() {
		return hikingDate;
	}

	public void setHikingDate(Timestamp hikingDate) {
		this.hikingDate = hikingDate;
	}

	@Override
	public String toString() {
		return "ChatLogResponse [serial=" + serial + ", content=" + content + ", createDate=" + createDate
				+ ", userSerial=" + userSerial + ", userName=" + userName + ", userId=" + userId + ", userPoint="
				+ userPoint + ", userGender=" + userGender + ", mountainSerial=" + mountainSerial + ", mountainName="
				+ mountainName + ", hikingDate=" + hikingDate + "]";
	}

}
