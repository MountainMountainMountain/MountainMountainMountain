package com.mountain.model.dto;

import java.sql.Timestamp;

public class Comment {
	private int serial;
	private String title;
	private int mountainSerial;
	private int userSerial;
	private String content;
	private Timestamp regDate;
	private Timestamp updateDate;
	private int calorie;
	private int turnaround;
	private int view_count;
	private int star;
	private String fileId;
	private String fileName;

	public Comment() {
	}

	public Comment(int serial, String title, int mountainSerial, int userSerial, String content, Timestamp regDate,
			Timestamp updateDate, int calorie, int turnaround, int view_count, int star, String fileId,
			String fileName) {
		this.serial = serial;
		this.title = title;
		this.mountainSerial = mountainSerial;
		this.userSerial = userSerial;
		this.content = content;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.calorie = calorie;
		this.turnaround = turnaround;
		this.view_count = view_count;
		this.star = star;
		this.fileId = fileId;
		this.fileName = fileName;
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

	public int getView_count() {
		return view_count;
	}

	public void setView_count(int view_count) {
		this.view_count = view_count;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "Comment [serial=" + serial + ", title=" + title + ", mountainSerial=" + mountainSerial + ", userSerial="
				+ userSerial + ", content=" + content + ", regDate=" + regDate + ", updateDate=" + updateDate
				+ ", calorie=" + calorie + ", turnaround=" + turnaround + ", view_count=" + view_count + ", star="
				+ star + ", fileId=" + fileId + ", fileName=" + fileName + "]";
	}

}
