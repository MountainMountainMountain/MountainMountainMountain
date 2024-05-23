package com.mountain.model.dto;

import java.sql.Timestamp;

public class CommentResponse {
	private int serial;
	private String title;
	private int mountainSerial;
	private String mountainName;
	private int userSerial;
	private String id;
	private String name;
	private String gender;
	private int point;
	private String content;
	private Timestamp regDate;
	private Timestamp updateDate;
	private int calorie;
	private int turnaround;
	private int viewCount;
	private int star;

	public CommentResponse() {
	}

	public CommentResponse(int serial, String title, int mountainSerial, String mountainName, int userSerial, String id,
			String name, String gender, int point, String content, Timestamp regDate, Timestamp updateDate, int calorie,
			int turnaround, int viewCount, int star) {
		this.serial = serial;
		this.title = title;
		this.mountainSerial = mountainSerial;
		this.mountainName = mountainName;
		this.userSerial = userSerial;
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.point = point;
		this.content = content;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.calorie = calorie;
		this.turnaround = turnaround;
		this.viewCount = viewCount;
		this.star = star;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getCalorie() {
		return calorie;
	}

	public void setCalorie(int calorie) {
		this.calorie = calorie;
	}

	public int getTurnaround() {
		return turnaround;
	}

	public void setTurnaround(int turnaround) {
		this.turnaround = turnaround;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	@Override
	public String toString() {
		return "CommentResponse [serial=" + serial + ", title=" + title + ", mountainSerial=" + mountainSerial
				+ ", mountainName=" + mountainName + ", userSerial=" + userSerial + ", id=" + id + ", name=" + name
				+ ", gender=" + gender + ", point=" + point + ", content=" + content + ", regDate=" + regDate
				+ ", updateDate=" + updateDate + ", calorie=" + calorie + ", turnaround=" + turnaround + ", viewCount="
				+ viewCount + ", star=" + star + "]";
	}

}
