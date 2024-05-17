package com.mountain.model.service;

import java.util.List;

import com.mountain.model.dto.Reply;

public interface ReplyService {

	// Comment의 Reply 전체 조회
	public List<Reply> selectByCommentSerial(int commentSerial);

	// 대댓글 1개 조회
	public Reply selectOneReply(int replySerial);

	// 대댓글 생성
	public void createReply(Reply reply);

	// 대댓글 수정
	void modifyReply(Reply reply);

	// 대댓글 삭제
	void deleteReply(int replySerial);

}
