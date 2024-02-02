package com.koreaIT.demo.service;

import org.springframework.stereotype.Service;

import com.koreaIT.demo.dao.ReplyDao;

@Service
public class ReplyService {
	
	private ReplyDao replyDao;
	
	public ReplyService(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}
	
	public void writeReply(int loginedMemberId, String relTypeCode, int relId, String body) {
		replyDao.writeArticle(loginedMemberId, relTypeCode, relId, body);
		
	}
}
