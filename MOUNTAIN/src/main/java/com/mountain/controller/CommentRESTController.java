package com.mountain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mountain.model.dto.Comment;
import com.mountain.model.dto.SearchConditionForComment;
import com.mountain.model.service.CommentService;
import com.mountain.util.JwtUtil;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api-comment")
public class CommentRESTController {

	@Autowired
	private JwtUtil jwtUtil;

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private final CommentService commentService;

	public CommentRESTController(CommentService commentService) {
		this.commentService = commentService;
	}

//	// 전체 게시글 조회
//	@GetMapping("/comment")
//	public ResponseEntity<List<Comment>> getAllMountainComments(){
//		
//	}

	// 해당 산의 리뷰 조회
	@GetMapping("/mountain/{mountainSerial}")
	public ResponseEntity<?> getCommentList(@PathVariable("mountainSerial") int mountainSerial) {
		List<Comment> commentList = commentService.selectCommentByMountainSerial(mountainSerial);
		return new ResponseEntity<List<Comment>>(commentList, HttpStatus.OK);
	}

	// Serial에 해당하는 게시글 조회
	@GetMapping("/comment/{commentSerial}")
	public ResponseEntity<?> selectOne(@PathVariable("commentSerial") int commentSerial) {
//		if() 없으면 어떻게 할까? list에 담아서 없으면 오류 출력해야 하나 
		return new ResponseEntity<Comment>(commentService.selectOne(commentSerial), HttpStatus.OK);
	}

	// 게시글 등록
	@PostMapping("/comment/create")
	public ResponseEntity<?> createComment(@RequestBody Comment comment) {
		System.out.println(comment);
		commentService.createComment(comment);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	// 게시글 삭제
	// 대댓글!!
	@DeleteMapping("/comment/delete/{commentSerial}")
	public ResponseEntity<?> deleteComment(@PathVariable("commentSerial") int commentSerial) {
		commentService.deleteComment(commentSerial);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// 게시글 수정
	@PutMapping("/comment/update")
	public ResponseEntity<?> modifyComment(@RequestBody Comment comment) {
		// 서버 인증 필요!!
		commentService.modifyComment(comment);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 게시글 검색 기능
	@GetMapping("/comment/search/{mountainSerial}")
	// ModelAttribute로 할지 param으로 할지 나중에 정하기
	public ResponseEntity<?> selectCommentBySearch(@PathVariable("mountainSerial") int mountainSerial,
			@ModelAttribute SearchConditionForComment searchCondition) {
		searchCondition.setMountainSerial(mountainSerial);
		List<Comment> list = commentService.selectCommentBySearch(searchCondition);
		return new ResponseEntity<List<Comment>>(list, HttpStatus.OK);
	}

	// 게시글 평점
	@GetMapping("/comment/star/{mountainSerial}")
	public ResponseEntity<?> selectStar(@PathVariable("mountainSerial") int mountainSerial) {
		return new ResponseEntity<>(commentService.selectStar(mountainSerial), HttpStatus.OK);
	}

}
