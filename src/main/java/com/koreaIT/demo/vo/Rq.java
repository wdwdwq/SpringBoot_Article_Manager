package com.koreaIT.demo.vo;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.koreaIT.demo.util.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rq {
	
	@Getter
	private int loginedMemberId;
	@Getter
	private String loginedMemberNickname;
	
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession session;
	
	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		
		this.req = req;
		this.resp = resp;
		
		this.session = req.getSession();
		
		int loginedMemberId = 0;
		
		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}

		this.loginedMemberNickname = (String) session.getAttribute("loginedMemberNickname");
		this.loginedMemberId = loginedMemberId;
		
		this.req.setAttribute("rq", this);
	}

	public void jsPrintHistoryBack(String msg) {
		resp.setContentType("text/html; charset=UTF-8;");
		
		try {
			resp.getWriter().append(Util.jsHistoryBack(msg));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void login(Member member) {
		this.session.setAttribute("loginedMemberId", member.getId());
		this.session.setAttribute("loginedMemberNickname", member.getNickname());
	}

	public void logout() {
		this.session.removeAttribute("loginedMemberId");
		this.session.removeAttribute("loginedMemberNickname");
	}

	public String jsReturnOnView(String msg) {
		
		this.req.setAttribute("msg", msg);
		
		return "usr/common/js";
	}

	public void init() {
	}
}