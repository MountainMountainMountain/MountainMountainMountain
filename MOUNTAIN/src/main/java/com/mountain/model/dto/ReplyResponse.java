package com.mountain.model.dto;

import java.sql.Timestamp;

public class ReplyResponse {
	private int serial;
	private int userSerial;
	private String id;
	private String name;
	private String gender;
	private int point;
	private int commentSerial;
	private String content;
	private Timestamp regDate;
	private Timestamp updateDate;

	public ReplyResponse() {
	}

	public ReplyResponse(int serial, int userSerial, int commentSerial, String content, Timestamp regDate,
			Timestamp updateDate) {
		this.serial = serial;
		this.userSerial = userSerial;
		this.commentSerial = commentSerial;
		this.content = content;
		this.regDate = regDate;
		this.updateDate = updateDate;
	}

	public ReplyResponse(int serial, int userSerial, String id, String name, String gender, int point,
			int commentSerial, String content, Timestamp regDate, Timestamp updateDate) {
		this.serial = serial;
		this.userSerial = userSerial;
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.point = point;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
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
		return "ReplyResponse [serial=" + serial + ", userSerial=" + userSerial + ", id=" + id + ", name=" + name
				+ ", gender=" + gender + ", point=" + point + ", commentSerial=" + commentSerial + ", content="
				+ content + ", regDate=" + regDate + ", updateDate=" + updateDate + "]";
	}

}
