package com.mountain.model.dto;

public class ChatUserManager {
	private int serial;
	private int chatInfoSerial;
	private int userSerial;

	public ChatUserManager() {
	}

	public ChatUserManager(int serial, int chatInfoSerial, int userSerial) {
		this.serial = serial;
		this.chatInfoSerial = chatInfoSerial;
		this.userSerial = userSerial;
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

	@Override
	public String toString() {
		return "ChatUserManager [serial=" + serial + ", chatInfoSerial=" + chatInfoSerial + ", userSerial=" + userSerial
				+ "]";
	}

}
