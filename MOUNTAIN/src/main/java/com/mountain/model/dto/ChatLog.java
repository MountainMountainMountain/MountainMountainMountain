package com.mountain.model.dto;

import java.sql.Timestamp;

public class ChatLog {
	private int serial;
	private int chatInfoSerial;
	private int userSerial;
	private String content;
	private Timestamp createDate;
	private int mountainSerial;
	private String mountainName;
	private Timestamp date;
	private String userName;
	private int point;

	public ChatLog(int serial, int chatInfoSerial, int userSerial, String content, Timestamp createDate,
			int mountainSerial, String mountainName, Timestamp date, String userName, int point) {
		this.serial = serial;
		this.chatInfoSerial = chatInfoSerial;
		this.userSerial = userSerial;
		this.content = content;
		this.createDate = createDate;
		this.mountainSerial = mountainSerial;
		this.mountainName = mountainName;
		this.date = date;
		this.userName = userName;
		this.point = point;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public int getChatInfoSerial() {
		return chatInfoSerial;
	}

	public void setChatInfoSerial(int chatInfoSerial) {
		this.chatInfoSerial = chatInfoSerial;
	}

	public int getUserSerial() {
		return userSerial;
	}

	public void setUserSerial(int userSerial) {
		this.userSerial = userSerial;
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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "ChatLog [serial=" + serial + ", chatInfoSerial=" + chatInfoSerial + ", userSerial=" + userSerial
				+ ", content=" + content + ", createDate=" + createDate + ", mountainSerial=" + mountainSerial
				+ ", mountainName=" + mountainName + ", date=" + date + ", userName=" + userName + ", point=" + point
				+ "]";
	}

}
