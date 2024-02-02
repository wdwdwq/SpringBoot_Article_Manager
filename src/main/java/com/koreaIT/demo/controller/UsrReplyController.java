package com.koreaIT.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.demo.service.ReplyService;
import com.koreaIT.demo.util.Util;
import com.koreaIT.demo.vo.Rq;

@Controller
public class UsrReplyController {

	private ReplyService replyService;
	private Rq rq;

	public UsrReplyController(ReplyService replyService, Rq rq) {
		this.replyService = replyService;
		this.rq = rq;
	}

	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public String doWrite(String relTypeCode, int relId, String body) {

		replyService.writeReply(rq.getLoginedMemberId(), relTypeCode, relId, body);

		return Util.f("<script>location.replace('../article/detail?id=%d')</script>", relId);
	}

}