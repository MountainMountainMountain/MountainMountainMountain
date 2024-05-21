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

import com.mountain.model.dto.Reply;
import com.mountain.model.service.ReplyService;

@RestController
@RequestMapping("/api-reply")
public class ReplyRESTController {

	private final ReplyService replyService;

	public ReplyRESTController(ReplyService replyService) {
		this.replyService = replyService;
	}

	@GetMapping("/{commentSerial}")
	public ResponseEntity<?> getReplyByCommentSerial(@PathVariable("commentSerial") int commentSerial) {
		List<Reply> list = replyService.selectByCommentSerial(commentSerial);

		return new ResponseEntity<List<Reply>>(list, HttpStatus.OK);
	}

	@GetMapping("/reply/{replySerial}")
	public ResponseEntity<?> getReply(@PathVariable("replySerial") int replySerial) {
		Reply reply = replyService.selectOneReply(replySerial);

		return new ResponseEntity<Reply>(reply, HttpStatus.OK);
	}

	@PostMapping("/reply")
	public ResponseEntity<?> createReply(@RequestBody Reply reply) {
		System.out.println(reply);
		replyService.createReply(reply);

		return new ResponseEntity<Reply>(reply, HttpStatus.CREATED);
	}

	@PutMapping("/reply/{replySerial}")
	public ResponseEntity<?> updateReply(@PathVariable("replySerial") int replySerial, @RequestBody Reply reply) {
		reply.setSerial(replySerial);
		replyService.modifyReply(reply);

		return new ResponseEntity<Reply>(reply, HttpStatus.OK);
	}

	@DeleteMapping("/{replySerial}")
	public ResponseEntity<?> deleteReply(@PathVariable("replySerial") int replySerial) {
		replyService.deleteReply(replySerial);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
