package com.mountain.model.dao;

import java.util.List;

import com.mountain.model.dto.Chat;
import com.mountain.model.dto.ChatLogResponse;

public interface ChatDao {

	// 전체 채팅 조회
	List<ChatLogResponse> selectAll();

	// 채팅정보 시리얼을 가진 chatlist
	List<ChatLogResponse> selectChatByChatInfoSerial(int chatInfoSerial);

	// 채팅 1개 조회
	ChatLogResponse selectOne(int chatSerial);

	// 채팅 생성
	void createChat(Chat chat);

	// 채팅 수정
	void modifyChat(Chat chat);

	// 채팅 삭제
	void deleteChat(int chatSerial);
	
}
