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
	private String fileId;
	private String fileName;
}
