package com.mountain.model.dto;

import java.sql.Timestamp;

public class Chat {
	private Long serial;
	private int chatUserManagerSerial;
	private String content;
	private Timestamp createDate;

	public Chat() {
	}

	public Chat(int chatUserManagerSerial, String content) {
		this.chatUserManagerSerial = chatUserManagerSerial;
		this.content = content;
	}

	public Chat(Long serial, int chatUserManagerSerial, String content, Timestamp createDate) {
		this.serial = serial;
		this.chatUserManagerSerial = chatUserManagerSerial;
		this.content = content;
		this.createDate = createDate;
	}

	public Long getSerial() {
		return serial;
	}

	public void setSerial(Long serial) {
		this.serial = serial;
	}

	public int getChatUserManagerSerial() {
		return chatUserManagerSerial;
	}

	public void setChatUserManagerSerial(int chatUserManagerSerial) {
		this.chatUserManagerSerial = chatUserManagerSerial;
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
		return "Chat [serial=" + serial + ", chatUserManagerSerial=" + chatUserManagerSerial + ", content=" + content
				+ ", createDate=" + createDate + "]";
	}

}
