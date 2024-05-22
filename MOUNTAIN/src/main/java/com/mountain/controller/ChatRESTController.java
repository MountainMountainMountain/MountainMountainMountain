package com.mountain.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mountain.model.dto.Chat;
import com.mountain.model.dto.ChatInfo;
import com.mountain.model.dto.ChatLogResponse;
import com.mountain.model.dto.ChatUserManager;
import com.mountain.model.service.ChatService;

@RestController
@RequestMapping("/api-chat")
public class ChatRESTController {

	private final ChatService chatService;

	public ChatRESTController(ChatService chatService) {
		this.chatService = chatService;
	}

	// 채팅 생성
	@PostMapping("/chat")
	public ResponseEntity<?> createChat(@RequestBody Chat chat) {
		chatService.createChat(chat);

		return new ResponseEntity<Chat>(chat, HttpStatus.CREATED);
	}

	// 채팅 1개 조회
	@GetMapping("/chat/{chatSerial}")
	public ResponseEntity<?> selectOneChat(@PathVariable("chatSerial") int chatSerial) {
		// responsebody에 User 정보(유저 이름, point) Mountain 정보(산 이름, 산 시리얼) 더 필요함
		ChatLogResponse chat = chatService.selectChat(chatSerial);

		return new ResponseEntity<ChatLogResponse>(chat, HttpStatus.OK);
	}

	// 채팅 수정
	@PutMapping("/chat/{chatSerial}")
	public ResponseEntity<?> modifyChat(@PathVariable("chatSerial") long chatSerial, @RequestBody Chat chat) {
		chat.setSerial(chatSerial);
		chatService.modifyChat(chat);

		return new ResponseEntity<Chat>(chat, HttpStatus.CREATED);
	}

	// 채팅 삭제
	@DeleteMapping("/chat/{chatSerial}")
	public ResponseEntity<?> deleteChat(@PathVariable("chatSerial") int chatSerial) {
		chatService.deleteChat(chatSerial);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 채팅 infoserial 통해서 리스트 가져오기
	@GetMapping("/chat/bychatinfo/{chatInfoSerial}")
	public ResponseEntity<?> selectChatByChatInfoSerial(@PathVariable("chatInfoSerial") int chatInfoSerial) {
		System.out.println(chatInfoSerial);
		// responsebody에 User 정보(유저 이름, point) Mountain 정보(산 이름, 산 시리얼) 더 필요함
		List<ChatLogResponse> list = chatService.selectChatByChatInfoSerial(chatInfoSerial);

		return new ResponseEntity<List<ChatLogResponse>>(list, HttpStatus.OK);
	}

	// 채팅Info 생성
	@PostMapping("/chatInfo")
	public ResponseEntity<?> createChatInfo(@RequestBody ChatInfo chatinfo) {
		chatService.createChatInfo(chatinfo);

		return new ResponseEntity<ChatInfo>(chatinfo, HttpStatus.CREATED);
	}

	// 채팅Info 1개 조회
	@GetMapping("/chatInfo/{chatInfoSerial}")
	public ResponseEntity<?> selectOneChatInfo(@PathVariable("chatInfoSerial") int chatInfoSerial) {
		ChatInfo chatinfo = chatService.selectChatInfo(chatInfoSerial);

		return new ResponseEntity<ChatInfo>(chatinfo, HttpStatus.OK);
	}

	// 채팅Info 수정
	@PutMapping("/chatInfo/{chatInfoSerial}")
	public ResponseEntity<?> modifyChatInfo(@PathVariable("chatInfoSerial") int chatInfoSerial,
			@RequestBody ChatInfo chatinfo) {
		chatinfo.setSerial(chatInfoSerial);
		chatService.modifyChatInfo(chatinfo);

		return new ResponseEntity<ChatInfo>(chatinfo, HttpStatus.CREATED);
	}

	// 채팅Info 삭제
	@DeleteMapping("/chatInfo/{chatInfoSerial}")
	public ResponseEntity<?> deleteChatInfo(@PathVariable("chatInfoSerial") int chatInfoSerial) {
		chatService.deleteChatInfo(chatInfoSerial);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 채팅Manager 생성
	@PostMapping("/chatManager")
	public ResponseEntity<?> createChatManager(@RequestBody ChatUserManager chatUserManager) {
		chatService.createChatUserManager(chatUserManager);

		return new ResponseEntity<ChatUserManager>(chatUserManager, HttpStatus.CREATED);
	}

	// 채팅Manager 1개 조회
	@GetMapping("/chatManager/{chatManagerSerial}")
	public ResponseEntity<?> selectOneChatManager(@PathVariable("chatManagerSerial") int chatManagerSerial) {
		ChatUserManager chatUserManager = chatService.selectChatUserManager(chatManagerSerial);

		return new ResponseEntity<ChatUserManager>(chatUserManager, HttpStatus.OK);
	}

	// 채팅Manager 수정
	@PutMapping("/chatManager/{chatManagerSerial}")
	public ResponseEntity<?> modifyChatManager(@PathVariable("chatManagerSerial") int chatManagerSerial,
			@RequestBody ChatUserManager chatUserManager) {
		chatUserManager.setSerial(chatManagerSerial);
		chatService.modifyChatUserManager(chatUserManager);

		return new ResponseEntity<ChatUserManager>(chatUserManager, HttpStatus.CREATED);
	}

	// 채팅Manager 삭제
	@DeleteMapping("/chatManager/{chatManagerSerial}")
	public ResponseEntity<?> deleteChatManager(@PathVariable("chatManagerSerial") int chatManagerSerial) {
		// 로그인 계정 필요함 그래서 오른쪽 int에 userSerial을 넣어야함
		chatService.deleteChatUserManager(chatManagerSerial, chatManagerSerial);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 채팅 참여
	@GetMapping("/chatUserManager/{chatInfoSerial}/{userSerial}")
	public ResponseEntity<?> joinChatUserManager(@PathVariable("chatInfoSerial") int chatInfoSerial,
			@PathVariable("userSerial") int userSerial) {

		chatService.joinChatUserManager(chatInfoSerial, userSerial);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 내가 참여한 채팅 확인
	@GetMapping("/chatUserManager/{userSerial}")
	public ResponseEntity<?> selectChatUserManagerByUserSerial(@PathVariable("userSerial") int userSerial) {
		List<ChatInfo> list = chatService.selectMyChatInfo(userSerial);

		return new ResponseEntity<List<ChatInfo>>(list, HttpStatus.OK);
	}

}
