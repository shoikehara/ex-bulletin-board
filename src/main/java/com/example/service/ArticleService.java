package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Article;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

/**
 * 記事を操作するサービスクラス.
 * 
 * @author sho.ikehara
 *
 */
@Service
@Transactional
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private CommentRepository commentRepository;
	
	/**
	 * 全件検索を行う.
	 * 
	 * @return 記事一覧
	 */
	public List<Article> findAll(){
		return articleRepository.findAll();
	}
	
	/**
	 * 記事を投稿する.
	 * 
	 * @param article 記事情報
	 */
	public void insert(Article article) {
		articleRepository.insert(article);
	}
	
	/**
	 * 記事を削除する.
	 * 
	 * @param id 記事ID
	 */
	public void delete(int id) {
		articleRepository.delete(id);
		commentRepository.deleteByArticleId(id);
	}
}
