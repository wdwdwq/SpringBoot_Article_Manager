package com.koreaIT.demo.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.koreaIT.demo.vo.RecommendPoint;

@Mapper
public interface RecommendPointDao {
	
	@Select("""
			SELECT *
				FROM recommendPoint
				WHERE memberId = #{loginedMemberId}
				AND relTypeCode = #{relTypeCode}
				AND relId = #{relId}
			""")
	public RecommendPoint getRecommendPoint(int loginedMemberId, String relTypeCode, int relId);
	
	@Insert("""
			INSERT INTO recommendPoint
				SET memberId = #{loginedMemberId}
					, relTypeCode = #{relTypeCode}
					, relId = #{relId}
					, `point` = 1
			""")
	public void insertPoint(int loginedMemberId, String relTypeCode, int relId);

	@Delete("""
			DELETE FROM recommendPoint
				WHERE memberId = #{loginedMemberId}
				AND relTypeCode = #{relTypeCode}
				AND relId = #{relId}
			""")
	public void deletePoint(int loginedMemberId, String relTypeCode, int relId);
}
