package com.mountain.model.dao;

import java.util.List;

import com.mountain.model.dto.Comment;
import com.mountain.model.dto.SearchConditionForComment;

public interface CommentDao {

	// 전체 게시글을 조회
	List<Comment> selectAll();

	// 산의 게시글 조회
	List<Comment> selectCommentByMountain(int mountainSerial);

	// Serial에 해당하는 게시글 하나 가져오기
	Comment selectOne(int commentSerial);

	// 게시글 등록
	void createComment(Comment comment);

	// 게시글 삭제
	void deleteComment(int commentSerial);

	// 게시글 수정
	void modifyComment(Comment comment);

	// 조회수 증가
	void modifyViewCnt(int commentSerial);

	// 검색 기능
	List<Comment> selectCommentBySearch(SearchConditionForComment searchCondition);

	// 평점 구하기
	int selectStar(int mountainSerial, int starNumber);

	// 등반 횟수 구하기
	int turnAroundTime(int mountainSerial);

	// 칼로리 평균 구하기
	int averageCalorie(int mountainSerial);

	// 평균 시간 구하기
	int averageTurnAround(int mountainSerial);

	// 파일 이름, ID 등 저장
	void insertFile(Comment comment);

}
