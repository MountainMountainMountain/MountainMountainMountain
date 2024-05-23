package com.mountain.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mountain.model.dao.ChatDao;
import com.mountain.model.dao.ChatInfoDao;
import com.mountain.model.dao.ChatUserManagerDao;
import com.mountain.model.dto.Chat;
import com.mountain.model.dto.ChatInfo;
import com.mountain.model.dto.ChatLogResponse;
import com.mountain.model.dto.ChatUserManager;

@Service
public class ChatServiceImpl implements ChatService {

	private final ChatDao chatDao;
	private final ChatInfoDao chatInfoDao;
	private final ChatUserManagerDao chatUserManagerDao;

	public ChatServiceImpl(ChatDao chatDao, ChatInfoDao chatInfoDao, ChatUserManagerDao chatUserManagerDao) {
		this.chatDao = chatDao;
		this.chatInfoDao = chatInfoDao;
		this.chatUserManagerDao = chatUserManagerDao;
	}

	// 채팅 info 생성
	@Override
	public void createChat(Chat Chat) {
		chatDao.createChat(Chat);
	}

	// 채팅 info 읽기
	@Override
	public ChatLogResponse selectChat(int chatSerial) {
		return chatDao.selectOne(chatSerial);
	}

	// 채팅 info 수정
	@Override
	public void modifyChat(Chat chat) {
		chatDao.modifyChat(chat);
	}

	// 채팅 info 삭제
	@Override
	public void deleteChat(int chatSerial) {
		chatDao.deleteChat(chatSerial);
	}

	// selectChatByChatInfoSerial
	@Override
	public List<ChatLogResponse> selectChatByChatInfoSerial(int chatInfoSerial) {
		return chatDao.selectChatByChatInfoSerial(chatInfoSerial);
	}

	// 채팅 manager 생성
	@Override
	public void createChatInfo(ChatInfo chatInfo) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("mountainSerial", chatInfo.getMountainSerial());
		paramMap.put("date", chatInfo.getDate());

		chatInfoDao.createChatInfo(paramMap);
	}

	// 채팅 manager 읽기
	@Override
	public ChatInfo selectChatInfo(int chatInfoSerial) {
		return chatInfoDao.selectChatInfoByChatInfoSerial(chatInfoSerial);
	}

	// 채팅 manager 수정
	@Override
	public void modifyChatInfo(ChatInfo chatInfo) {
		chatInfoDao.modifyChatInfo(chatInfo);
	}

	// 채팅 manager 삭제
	@Override
	public void deleteChatInfo(int chatInfoSerial) {
		chatInfoDao.deleteChatInfo(chatInfoSerial);
	}

	// 채팅 생성
	@Override
	public void createChatUserManager(ChatUserManager chatUserManager) {
		chatUserManagerDao.joinChat(chatUserManager.getChatInfoSerial(), chatUserManager.getUserSerial());
	}

	// 채팅 읽기
	@Override
	public ChatUserManager selectChatUserManager(int chatUserManagerSerial) {
		return chatUserManagerDao.selectOneByChatInfoSerial(chatUserManagerSerial);
	}

	// 채팅 수정
	@Override
	public void modifyChatUserManager(ChatUserManager chatUserManager) {
		chatUserManagerDao.modifyChatUserManager(chatUserManager);
	}

	// 채팅 삭제
	@Override
	public void deleteChatUserManager(int chatUserManagerSerial, int userSerial) {
		chatUserManagerDao.leaveChat(chatUserManagerSerial, userSerial);
	}

	// 채팅 참여
	@Override
	public void joinChatUserManager(int chatInfoSerial, int chatUserSerial) {
		chatUserManagerDao.joinChat(chatInfoSerial, chatUserSerial);
	}

	// 채팅 참여 리스트 불러오기
	@Override
	public List<ChatInfo> selectMyChatInfo(int userSerial) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("userSerial", userSerial);

		return chatInfoDao.selectMyChatInfoByMap(paramMap);
	}
}
