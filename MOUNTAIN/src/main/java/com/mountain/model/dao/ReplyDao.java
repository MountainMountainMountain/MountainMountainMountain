package com.mountain.model.dao;

import java.util.List;

import com.mountain.model.dto.Reply;
import com.mountain.model.dto.ReplyResponse;

public interface ReplyDao {

	// Comment의 Reply 전체 조회
	List<ReplyResponse> selectByCommentSerial(int commentSerial);
	
	// 대댓글 1개 조회
	ReplyResponse selectOneReply(int replySerial);

	// 대댓글 생성
	void createReply(Reply reply);
	
	// 대댓글 수정
	void modifyReply(Reply reply);
	
	// 대댓글 삭제
	void deleteReply(int replySerial);
	
}
