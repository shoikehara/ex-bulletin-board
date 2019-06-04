package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.service.ArticleCommentService;

@Controller
@RequestMapping("/bulletin-board2")
public class ArticleCommentController {
	@Autowired
	private ArticleCommentService articleCommentService;	
	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}
	/**
	 * 記事一覧とコメントを表示する.
	 * 
	 * @param model モデル
	 * @return　掲示板ページ
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Article> articleList = articleCommentService.findAll();
		model.addAttribute("articleList",articleList);
		return "bulletin-board2";
	}
	
	@RequestMapping("/articleInsert")
	public String articleInsert(@Validated ArticleForm articleForm,BindingResult result ,Model model) {
		Article article = new Article();
		if(result.hasErrors()) {
			return showList(model);
		}
		article.setName(articleForm.getName());
		article.setContent(articleForm.getContent());
		articleCommentService.insert(article);
		return "redirect:/bulletin-board2/showList";
	}
	
	@RequestMapping("/commentInsert")
	public String commentInsert(@Validated CommentForm commentForm,BindingResult result,Model model) {
		Comment comment = new Comment();
		if(result.hasErrors()) {
			return showList(model);
		}
		comment.setArticleId(Integer.parseInt(commentForm.getArticleId()));
		comment.setName(commentForm.getName());
		comment.setContent(commentForm.getContent());
		articleCommentService.insert(comment);
		return "redirect:/bulletin-board2/showList";
	}
	
	@RequestMapping("/delete")
	public String delete(Integer articleId) {
		articleCommentService.delete(articleId);
		return "redirect:/bulletin-board2/showList";
	}
}
