package com.koreaIT.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyDao {
	
	@Insert("""
			INSERT INTO reply
				SET regDate = NOW()
					, updateDate = NOW()
					, memberId = #{loginedMemberId}
					, relTypeCode = #{relTypeCode}
					, relId = #{relId}
					, `body` = #{body}
			""")
	public void writeArticle(int loginedMemberId, String relTypeCode, int relId, String body);
}
