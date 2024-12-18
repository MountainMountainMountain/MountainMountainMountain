package com.mountain.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mountain.model.dao.ReplyDao;
import com.mountain.model.dto.Reply;
import com.mountain.model.dto.ReplyResponse;

@Service
public class ReplyServiceImpl implements ReplyService {

	private final ReplyDao replyDao;

	public ReplyServiceImpl(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	@Override
	public List<ReplyResponse> selectByCommentSerial(int commentSerial) {
		return replyDao.selectByCommentSerial(commentSerial);
	}

	@Override
	public ReplyResponse selectOneReply(int replySerial) {
		return replyDao.selectOneReply(replySerial);
	}

	@Override
	public void createReply(Reply reply) {
		replyDao.createReply(reply);
	}

	@Override
	public void modifyReply(Reply reply) {
		replyDao.modifyReply(reply);
	}

	@Override
	public void deleteReply(int replySerial) {
		replyDao.deleteReply(replySerial);
	}

}
