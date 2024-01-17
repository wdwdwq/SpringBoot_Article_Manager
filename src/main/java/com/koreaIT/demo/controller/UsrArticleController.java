package com.koreaIT.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.demo.service.ArticleService;
import com.koreaIT.demo.util.Util;
import com.koreaIT.demo.vo.Article;
import com.koreaIT.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrArticleController {
	
	private ArticleService articleService;
	
	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData<Article> doWrite(HttpSession session, String title, String body) {
		
		if (session.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-L", "로그인 후 이용해주세요");
		}
		
		if (Util.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}
		
		if (Util.empty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요");
		}
		
		articleService.writeArticle((int) session.getAttribute("loginedMemberId"), title, body);
		
		int id = articleService.getLastInsertId();
		
		return ResultData.from("S-1", Util.f("%d번 게시물을 생성했습니다", id), articleService.getArticleById(id));
	}
	
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public ResultData<List<Article>> showList() {
		
		List<Article> articles = articleService.getArticles();
		
		if (articles.size() == 0) {
			return ResultData.from("F-1", "게시물이 존재하지 않습니다");
		}
		
		return ResultData.from("S-1", "게시물 목록", articles);
	}
	
	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public ResultData<Article> showDetail(int id) {
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다", id));
		}
		
		return ResultData.from("S-1", Util.f("%d번 게시물", id), article);
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(HttpSession session, int id, String title, String body) {
		
		if (session.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-L", "로그인 후 이용해주세요");
		}
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다", id));
		}
		
		if (article.getMemberId() != (int) session.getAttribute("loginedMemberId")) {
			return ResultData.from("F-A", "해당 게시물에 대한 권한이 없습니다");
		}
		
		articleService.modifyArticle(id, title, body);
		
		return ResultData.from("S-1", Util.f("%d번 게시물을 수정했습니다", id));
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(HttpSession session, int id) {
		
		if (session.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-L", "로그인 후 이용해주세요");
		}
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다", id));
		}
		
		if (article.getMemberId() != (int) session.getAttribute("loginedMemberId")) {
			return ResultData.from("F-A", "해당 게시물에 대한 권한이 없습니다");
		}
		
		articleService.deleteArticle(id);
		
		return ResultData.from("S-1", Util.f("%d번 게시물을 삭제했습니다", id));
	}
}