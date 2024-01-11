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
		this.lastArticleId = 0;
		this.articles = new ArrayList<>();

	}

	@RequestMapping("/user/article/doWrite")
	@ResponseBody
	public Article doWrite(String title, String body) {

		this.lastArticleId++;
		Article article = new Article(lastArticleId, title, body);

		this.articles.add(article);

		return article;
	}

	@RequestMapping("/user/article/list")
	@ResponseBody
	public List<Article> ShowList() {

		return this.articles;
	}

}
