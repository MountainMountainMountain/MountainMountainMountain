package com.mountain.model.dao;

import com.mountain.model.dto.ChatUserManager;

public interface ChatUserManagerDao {

	// 채팅 참여자 수 구하기
	int getParticipantCount(int chatInfoSerial);

	// 채팅 참여하기
	void joinChat(int chatInfoSerial, int userSerial);

	// 채팅
	void updateChatUserManager(ChatUserManager chatUserManager);

	// 채팅 나가기
	void leaveChat(int chatInfoSerial, int userSerial);

}
