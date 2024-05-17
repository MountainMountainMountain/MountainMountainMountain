package com.mountain.model.dto;

import java.sql.Timestamp;

public class ChatInfo {
	private int serial;
	private int mountainSerial;
	private Timestamp date;

	public ChatInfo() {
	}

	public ChatInfo(int serial, int mountainSerial, Timestamp date) {
		this.serial = serial;
		this.mountainSerial = mountainSerial;
		this.date = date;
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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ChatInfo [serial=" + serial + ", mountainSerial=" + mountainSerial + ", date=" + date + "]";
	}

}
