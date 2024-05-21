package com.mountain.model.service;

import java.util.List;

import com.mountain.model.dto.Chat;
import com.mountain.model.dto.ChatInfo;
import com.mountain.model.dto.ChatLogResponse;
import com.mountain.model.dto.ChatUserManager;

public interface ChatService {

	// 채팅 info 생성
	void createChatInfo(ChatInfo chatInfo);

	// 채팅 info 읽기
	ChatInfo selectChatInfo(int chatInfoSerial);

	// 채팅 info 수정
	void modifyChatInfo(ChatInfo chatInfo);

	// 채팅 info 삭제
	void deleteChatInfo(int chatInfoSerial);

	// 채팅 manager 생성
	void createChatUserManager(ChatUserManager chatUserManager);

	// 채팅 manager 읽기
	ChatUserManager selectChatUserManager(int chatUserManagerSerial);

	// 채팅 manager 수정
	void modifyChatUserManager(ChatUserManager chatUserManager);

	// 채팅 manager 삭제
	void deleteChatUserManager(int chatUserManagerSerial, int userSerial);

	// 채팅 생성
	void createChat(Chat Chat);

	// 채팅 읽기
	ChatLogResponse selectChat(int chatSerial);

	// 채팅 수정
	void modifyChat(Chat chat);

	// 채팅 삭제
	void deleteChat(int chatSerial);

	// selectChatByChatInfoSerial
	List<ChatLogResponse> selectChatByChatInfoSerial(int chatInfoSerial);

}
