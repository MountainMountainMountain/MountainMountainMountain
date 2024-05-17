package com.mountain.model.dto;

import java.sql.Timestamp;

public class Chat {
	private int serial;
	private int chatInfoSerial;
	private int userSerial;
	private String content;
	private Timestamp createDate;

	public Chat() {
	}

	public Chat(int serial, int chatInfoSerial, int userSerial, String content, Timestamp createDate) {
		this.serial = serial;
		this.chatInfoSerial = chatInfoSerial;
		this.userSerial = userSerial;
		this.content = content;
		this.createDate = createDate;
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

	@Override
	public String toString() {
		return "Chat [serial=" + serial + ", chatInfoSerial=" + chatInfoSerial + ", userSerial=" + userSerial
				+ ", content=" + content + ", createDate=" + createDate + "]";
	}

}
