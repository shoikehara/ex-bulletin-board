package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.service.ArticleService;
import com.example.service.CommentService;

@Controller
@RequestMapping("/bulletin-board")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CommentService commentService;
	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}
	
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Article> articleList = articleService.findAll();
		model.addAttribute("articleList",articleList);
		return "bulletin-board";
	}
	@RequestMapping("/insert")
	public String insert(ArticleForm articleForm,CommentForm commentForm,Model model) {
		Article article = new Article();
		article.setName(articleForm.getName());
		article.setContent(articleForm.getContent());
		articleService.insert(article);
		List<Article> articleList = articleService.findAll();
		model.addAttribute("articleList",articleList);
		
		Comment comment = new Comment();
		comment.setArticleId(Integer.parseInt(commentForm.getArticleId()));
		comment.setName(commentForm.getName());
		comment.setContent(commentForm.getContent());
		List<Comment> commentList = commentService.findByArticleId(Integer.parseInt(commentForm.getArticleId()));
		model.addAttribute("commentList",commentList);
		
		return "bulletin-board";
	}
}
