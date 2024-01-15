package com.koreaIT.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.demo.service.ArticleService;
import com.koreaIT.demo.vo.Article;

@Controller
public class UsrArticleController {
	
	private ArticleService articleService;
	
	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public Article doWrite(String title, String body) {
		articleService.writeArticle(title, body);
		
		int id = articleService.getLastInsertId();
		
		return articleService.getArticleById(id);
	}
	
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList() {
		return articleService.getArticles();
	}
	
	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Object showDetail(int id) {
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return id + "번 게시물은 존재하지 않습니다";
		}
		
		return article;
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return id + "번 게시물은 존재하지 않습니다";
		}
		
		articleService.modifyArticle(id, title, body);
		
		return id + "번 게시물을 수정했습니다";
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return id + "번 게시물은 존재하지 않습니다";
		}
		
		articleService.deleteArticle(id);
		
		return id + "번 게시물을 삭제했습니다";
	}
}