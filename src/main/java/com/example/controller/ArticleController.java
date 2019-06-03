package com.example.controller;

import java.util.ArrayList;
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

/**
 * 記事を操作するコントローラクラス.
 * 
 * @author sho.ikehara
 *
 */
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
	
	/**
	 * 記事一覧を表示する.
	 * 
	 * @param model モデル
	 * @return　掲示板ページ
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Article> articleList = articleService.findAll();		
		List<Comment> commentList = new ArrayList<>();
		for(Article article : articleList) {
			commentList = commentService.findByArticleId(article.getId());
			article.setCommentList(commentList);
		}
		model.addAttribute("articleList",articleList);
		return "bulletin-board";
	}
	/**
	 * 記事投稿をする.
	 * 
	 * @param articleForm 入力された記事情報
	 * @param model　モデル
	 * @return　掲示板ページ
	 */
	@RequestMapping("/articleInsert")
	public String articleInsert(ArticleForm articleForm,Model model) {
		Article article = new Article();
		article.setName(articleForm.getName());
		article.setContent(articleForm.getContent());
		articleService.insert(article);
		List<Article> articleList = articleService.findAll();
		model.addAttribute("articleList",articleList);
		
		return "redirect:/bulletin-board/showList";
	}
	/**
	 * コメントを投稿する
	 * 
	 * @param commentForm　入力されたコメント情報
	 * @param model　モデル
	 * @return　掲示板ページ
	 */
	@RequestMapping("/commentInsert")
	public String commentInsert(CommentForm commentForm,Model model) {
		Comment comment = new Comment();
		comment.setArticleId(Integer.parseInt(commentForm.getArticleId()));
		comment.setName(commentForm.getName());
		comment.setContent(commentForm.getContent());
		commentService.insert(comment);
		List<Comment> commentList = commentService.findByArticleId(Integer.parseInt(commentForm.getArticleId()));
		model.addAttribute("commentList",commentList);

		return "redirect:/bulletin-board/showList";
	}
}
