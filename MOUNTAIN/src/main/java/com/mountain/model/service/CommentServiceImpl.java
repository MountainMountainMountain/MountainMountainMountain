package com.mountain.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mountain.model.dao.CommentDao;
import com.mountain.model.dto.Comment;
import com.mountain.model.dto.CommentResponse;
import com.mountain.model.dto.SearchConditionForComment;

@Service
public class CommentServiceImpl implements CommentService {

	private final CommentDao commentDao;

	public CommentServiceImpl(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Override
	public List<CommentResponse> selectAll() {
		return commentDao.selectAll();
	}

	@Override
	public List<CommentResponse> selectCommentByMountainSerial(int mountainSerial) {
//		SearchCondition searchCondition = new SearchCondition();
//		searchCondition.setKey(mountainSerial + "");
//		searchCondition.setWord(mountainSerial + "");
//		return commentDao.selectCommentBySearch(searchCondition);
		// selectCommentByMountain 사용할지 말지 나중에 고민
		return commentDao.selectCommentByMountain(mountainSerial);
	}

	@Override
	public CommentResponse selectOne(int commentSerial) {
		commentDao.modifyViewCnt(commentSerial);
		return commentDao.selectOne(commentSerial);
	}

	@Override
	public void createComment(Comment comment) {
		commentDao.createComment(comment);
	}

	@Override
	public void deleteComment(int commentSerial) {
		commentDao.deleteComment(commentSerial);
	}

	@Override
	public void modifyComment(Comment comment) {
		commentDao.modifyComment(comment);
	}

	@Override
	public List<CommentResponse> selectCommentBySearch(SearchConditionForComment searchCondition) {
		return commentDao.selectCommentBySearch(searchCondition);
	}

	@Override
	public Map<String, Integer> selectStar(int mountainSerial) {
		Map<String, Integer> starInfo = new HashMap<String, Integer>();
		starInfo.put("1", commentDao.selectStar(mountainSerial, 1));
		starInfo.put("2", commentDao.selectStar(mountainSerial, 2));
		starInfo.put("3", commentDao.selectStar(mountainSerial, 3));
		starInfo.put("4", commentDao.selectStar(mountainSerial, 4));
		starInfo.put("5", commentDao.selectStar(mountainSerial, 5));

		return starInfo;
	}

	@Override
	public int turnAroundTime(int mountainSerial) {
		return commentDao.averageTurnAround(mountainSerial);
	}

	@Override
	public int averageCalorie(int mountainSerial) {
		return commentDao.averageCalorie(mountainSerial);
	}

	@Override
	public int averageTurnAround(int mountainSerial) {
		return commentDao.averageTurnAround(mountainSerial);
	}

}
