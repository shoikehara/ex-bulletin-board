package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.repository.ArticleCommentRepository;

@Service
@Transactional
public class ArticleCommentService {
	@Autowired
	private ArticleCommentRepository articleCommentRepository;
	
	public List<Article> findAll(){
		return articleCommentRepository.findAll();
	}
	
	public void insert(Article article) {
		articleCommentRepository.insert(article);
	}
	
	public void insert(Comment comment) {
		articleCommentRepository.insert(comment);
	}
	
	public void delete(Integer articleId) {
		articleCommentRepository.delete(articleId);
	}
}
