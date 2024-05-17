package com.mountain.model.dto;

import java.sql.Timestamp;

public class Reply {
	private int serial;
	private int userSerial;
	private int commentSerial;
	private String content;
	private Timestamp regDate;
	private Timestamp updateDate;

	public Reply() {
	}

	public Reply(int serial, int userSerial, int commentSerial, String content, Timestamp regDate,
			Timestamp updateDate) {
		this.serial = serial;
		this.userSerial = userSerial;
		this.commentSerial = commentSerial;
		this.content = content;
		this.regDate = regDate;
		this.updateDate = updateDate;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public int getUserSerial() {
		return userSerial;
	}

	public void setUserSerial(int userSerial) {
		this.userSerial = userSerial;
	}

	public int getCommentSerial() {
		return commentSerial;
	}

	public void setCommentSerial(int commentSerial) {
		this.commentSerial = commentSerial;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Reply [serial=" + serial + ", userSerial=" + userSerial + ", commentSerial=" + commentSerial
				+ ", content=" + content + ", regDate=" + regDate + ", updateDate=" + updateDate + "]";
	}

}
