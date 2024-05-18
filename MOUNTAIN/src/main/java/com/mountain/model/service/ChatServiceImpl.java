package com.mountain.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mountain.model.dao.ChatDao;
import com.mountain.model.dao.ChatInfoDao;
import com.mountain.model.dao.ChatUserManagerDao;
import com.mountain.model.dto.Chat;
import com.mountain.model.dto.ChatInfo;
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

	@Override
	public void createChat(Chat Chat) {
		chatDao.createChat(Chat);
	}

	@Override
	public Chat selectChat(int chatSerial) {
		return chatDao.selectOne(chatSerial);
	}

	@Override
	public void modifyChat(Chat chat) {
		chatDao.modifyChat(chat);
	}

	@Override
	public void deleteChat(int chatSerial) {
		chatDao.deleteChat(chatSerial);
	}

	@Override
	public void createChatInfo(ChatInfo chatInfo) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("mountainSerial", chatInfo.getMountainSerial());
		paramMap.put("date", chatInfo.getDate());

		chatInfoDao.createChatInfo(paramMap);
	}

	@Override
	public ChatInfo selectChatInfo(int chatInfoSerial) {
		return chatInfoDao.selectChatInfoByChatInfoSerial(chatInfoSerial);
	}

	@Override
	public void modifyChatInfo(ChatInfo chatInfo) {
		chatInfoDao.modifyChatInfo(chatInfo);
	}

	@Override
	public void deleteChatInfo(int chatInfoSerial) {
		chatInfoDao.deleteChatInfo(chatInfoSerial);
	}

	@Override
	public void createChatUserManager(ChatUserManager chatUserManager) {
		chatUserManagerDao.joinChat(chatUserManager.getChatInfoSerial(), chatUserManager.getUserSerial());
	}

	@Override
	public ChatUserManager selectChatUserManager(int chatUserManagerSerial) {
		return chatUserManagerDao.selectOneByChatInfoSerial(chatUserManagerSerial);
	}

	@Override
	public void modifyChatUserManager(ChatUserManager chatUserManager) {
		chatUserManagerDao.modifyChatUserManager(chatUserManager);
	}

	@Override
	public void deleteChatUserManager(int chatUserManagerSerial, int userSerial) {
		chatUserManagerDao.leaveChat(chatUserManagerSerial, userSerial);
	}

}
