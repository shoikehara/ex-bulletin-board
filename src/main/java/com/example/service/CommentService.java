package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Comment;
import com.example.repository.CommentRepository;

/**
 * コメントを操作するサービスクラス.
 * 
 * @author sho.ikehara
 *
 */
@Service
@Transactional
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;
	
	/**
	 * コメントを検索する.
	 * 
	 * @param articleId 記事ID
	 * @return　コメント一覧
	 */
	public List<Comment> findByArticleId(int articleId){
		return commentRepository.findByArticleId(articleId);
	}
	
	/**
	 * コメントを投稿する.
	 * 
	 * @param comment コメント情報
	 */
	public void insert(Comment comment) {
		commentRepository.insert(comment);
	}
	
	/**
	 * コメントを削除する.
	 * 
	 * @param articleId 記事ID
	 */
//	public void deleteByArticleId(int articleId) {
//	}
}
