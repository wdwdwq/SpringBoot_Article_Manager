package com.koreaIT.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.demo.vo.Article;

@Controller
public class UserArticleController {
	
	private int lastArticleId;
	private List<Article> articles;
	
	public UserArticleController() {
		lastArticleId = 0;
		articles = new ArrayList<>();
		
		makeTestData();
	}
	
	// 서비스메서드
	private void makeTestData() {
		for (int i = 1; i <= 10; i++) {
			
			String title = "제목" + i;
			String body = "내용" + i;
			
			writeArticle(title, body);
		}
	}
	
	private Article writeArticle(String title, String body) {
		lastArticleId++;
		Article article = new Article(lastArticleId, title, body);
		articles.add(article);
		
		return article;
	}

	// 액션메서드
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public Article doWrite(String title, String body) {
		Article article = writeArticle(title, body);
		
		return article;
	}
	
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList() {
		return articles;
	}
	
}