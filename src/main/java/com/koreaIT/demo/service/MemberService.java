package com.koreaIT.demo.service;

import org.springframework.stereotype.Service;

import com.koreaIT.demo.dao.MemberDao;
import com.koreaIT.demo.vo.Member;

@Service
public class MemberService {
	
	private MemberDao memberDao;
	
	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void joinMember(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		memberDao.joinMember(loginId, loginPw, name, nickname, cellphoneNum, email);
	}
	
	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

	public int getLastInsertId() {
		return memberDao.getLastInsertId();
	}
}
