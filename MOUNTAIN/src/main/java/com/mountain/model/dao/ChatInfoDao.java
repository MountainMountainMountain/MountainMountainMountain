package com.mountain.model.dao;

import java.util.List;
import java.util.Map;

import com.mountain.model.dto.ChatInfo;

public interface ChatInfoDao {

	// 채팅 만들기
	void createChatInfo(Map<String, Object> paramMap);
	// 아래처럼 해야 함
	// paramMap.put("intValue", 123); // int 값 설정
	// paramMap.put("timestampValue", Timestamp.valueOf(LocalDateTime.now())); //
	// Timestamp 값 설정

	// 채팅 1개 찾기
	ChatInfo selectChatInfoByMap(Map<String, Object> paramMap);

	// 내 채팅 찾기
	List<ChatInfo> selectMyChatInfoByMap(Map<String, Object> paramMap);

	// 채팅
	ChatInfo selectChatInfoByChatInfoSerial(int chatInfoSerial);

	// 채팅 업데이트
	void modifyChatInfo(ChatInfo ChatInfo);

	// 채팅 삭제하기
	void deleteChatInfo(int chatInfoSerial);

}
