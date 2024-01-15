package com.koreaIT.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.koreaIT.demo.vo.Article;

@Mapper
public interface ArticleDao {
	
	public Article writeArticle(String title, String body);
	
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
			SELECT *
				FROM article
				ORDER BY id DESC
			""")
	public List<Article> getArticles();
}