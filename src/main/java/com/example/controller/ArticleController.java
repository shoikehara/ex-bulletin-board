package com.example.controller;

import java.util.ArrayList;
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
	 * 記事を投稿する.
	 * 
	 * @param articleForm 入力された記事情報
	 * @param result　エラーの内容格納
	 * @param model　モデル
	 * @return　掲示板ページ
	 */
	@RequestMapping("/articleInsert")
	public String articleInsert(@Validated ArticleForm articleForm,BindingResult result ,Model model) {
		Article article = new Article();
		if(result.hasErrors()) {
			return showList(model);
		}
		article.setName(articleForm.getName());
		article.setContent(articleForm.getContent());
		articleService.insert(article);
		
		return "redirect:/bulletin-board/showList";
	}
	
	/**
	 * コメントを投稿する.
	 * 
	 * @param commentForm 入力されたコメント情報
	 * @param result　エラーの内容格納
	 * @param model　モデル
	 * @return　掲示板ページ
	 */
	@RequestMapping("/commentInsert")
	public String commentInsert(@Validated CommentForm commentForm,BindingResult result,Model model) {
		Comment comment = new Comment();
		if(result.hasErrors()) {
			return showList(model);
		}
		comment.setArticleId(Integer.parseInt(commentForm.getArticleId()));
		comment.setName(commentForm.getName());
		comment.setContent(commentForm.getContent());
		commentService.insert(comment);

		return "redirect:/bulletin-board/showList";
	}
	
	/**
	 * 記事とコメントを削除する.
	 * 
	 * @param articleId 記事ID
	 * @return　掲示板ページ
	 */
	@RequestMapping("/delete")
	public String delete(Integer articleId) {
		articleService.delete(articleId);
		return "redirect:/bulletin-board/showList";
	}
}
