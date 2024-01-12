package com.koreaIT.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.koreaIT.demo.vo.Article;

@Component
public class ArticleDao {
	private int lastArticleId;
	private List<Article> articles;
	
	public ArticleDao() {
		lastArticleId = 0;
		articles = new ArrayList<>();
		
		makeTestData();
	}
	
	private void makeTestData() {
		for (int i = 1; i <= 10; i++) {
			
			String title = "제목" + i;
			String body = "내용" + i;
			
			writeArticle(title, body);
		}
	}
	
	public Article writeArticle(String title, String body) {
		lastArticleId++;
		Article article = new Article(lastArticleId, title, body);
		articles.add(article);
		
		return article;
	}
	
	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}
	
	public void modifyArticle(Article article, String title, String body) {
		article.setTitle(title);
		article.setBody(body);
	}
	
	public void deleteArticle(Article article) {
		articles.remove(article);
	}

	public List<Article> getArticles() {
		return articles;
	}
}
