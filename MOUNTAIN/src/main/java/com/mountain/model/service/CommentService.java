package com.mountain.model.service;

import java.util.List;
import java.util.Map;

import com.mountain.model.dto.Comment;
import com.mountain.model.dto.SearchCondition;

public interface CommentService {

	// 전체 게시글을 조회
	public List<Comment> selectAll();

	// 산의 게시글 조회
	public List<Comment> selectCommentByMountainSerial(int mountainSerial);

	// Serial에 해당하는 게시글 하나 가져오기
	public Comment selectOne(int commentSerial);

	// 게시글 등록
	public void createComment(Comment comment);

	// 게시글 삭제
	public void deleteComment(int commentSerial);

	// 게시글 수정
	public void modifyComment(Comment comment);

	// 검색 기능
	public List<Comment> selectCommentBySearch(SearchCondition searchCondition);

	// 평점 구하기
	public Map<String, Integer> selectStar(int mountainSerial);

	// 등반 횟수 구하기
	public int turnAroundTime(int mountainSerial);

	// 칼로리 평균 구하기
	public int averageCalorie(int mountainSerial);

	// 평균 시간 구하기
	public int averageTurnAround(int mountainSerial);

	// 파일 이름, ID 등 저장
}
