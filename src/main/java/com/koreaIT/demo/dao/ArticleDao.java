package com.koreaIT.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.koreaIT.demo.vo.Article;

@Mapper
public interface ArticleDao {
	
	@Insert("""
			INSERT INTO article
				SET regDate = NOW()
					, updateDate = NOW()
					, memberId = #{memberId}
					, boardId = #{boardId}
					, title = #{title}
					, `body` = #{body}
			""")
	public void writeArticle(int memberId, int boardId, String title, String body);
	
	@Select("""
			SELECT *
				FROM article
				WHERE id = #{id}
			""")
	public Article getArticleById(int id);
	
	@Update("""
			<script>
				UPDATE article
					SET updateDate = NOW()
						<if test="title != null and title != ''">
							, title = #{title}
						</if>
						<if test="body != null and body != ''">
							, `body` = #{body}
						</if>
					WHERE id = #{id}
			</script>
			""")
	public void modifyArticle(int id, String title, String body);
	
	@Delete("""
			DELETE FROM article
				WHERE id = #{id}
			""")
	public void deleteArticle(int id);

	@Select("""
			<script>
			SELECT A.*, M.nickname AS writerName
				FROM article AS A
				INNER JOIN `member` AS M
				ON A.memberId = M.id
				WHERE A.boardId = #{boardId}
				<if test="searchKeyword != ''">
					<choose>
						<when test="searchKeywordType == 'title'">
							AND A.title LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchKeywordType == 'body'">
							AND A.body LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<otherwise>
							AND (
								title LIKE CONCAT('%', #{searchKeyword}, '%')
								OR A.body LIKE CONCAT('%', #{searchKeyword}, '%')
							)
						</otherwise>
					</choose>
				</if>
				ORDER BY A.id DESC
				LIMIT #{limitStart}, #{itemsInAPage}
			</script>
			""")
	public List<Article> getArticles(int boardId, String searchKeywordType, String searchKeyword, int limitStart, int itemsInAPage);

	@Select("""
			SELECT LAST_INSERT_ID();
			""")
	public int getLastInsertId();

	@Select("""
			SELECT A.*, M.nickname AS writerName
				FROM article AS A
				INNER JOIN `member` AS M
				ON A.memberId = M.id
				WHERE A.id = #{id}
			""")
	public Article forPrintArticle(int id);

	@Select("""
			<script>
			SELECT COUNT(*)
				FROM article
				WHERE boardId = #{boardId}
				<if test="searchKeyword != ''">
					<choose>
						<when test="searchKeywordType == 'title'">
							AND title LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchKeywordType == 'body'">
							AND `body` LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<otherwise>
							AND (
								title LIKE CONCAT('%', #{searchKeyword}, '%')
								OR `body` LIKE CONCAT('%', #{searchKeyword}, '%')
							)
						</otherwise>
					</choose>
				</if>
			</script>
			""")
	public int getArticlesCnt(int boardId, String searchKeywordType, String searchKeyword);
}