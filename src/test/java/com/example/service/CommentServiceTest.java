package com.example.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Comment;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {
	@Autowired
	private CommentService commentService;
	
	@Test
	public void findByArticleIdTest() {
		System.out.println("ID検索するテスト開始");
		
		List<Comment> commentList = commentService.findByArticleId(1);
		
		assertThat("件数が一致しません", commentList.size(), is(3));
		assertThat("最初の内容が一致しません", commentList.get(0).getContent(), is("伊賀さん書き込みのコメント1"));
		assertThat("最後の内容が一致しません", commentList.get(2).getContent(), is("伊賀さん書き込みのコメント3"));
		
		System.out.println("ID検索するテスト終了");
	}
	@Test
	public void insertTest() {
		System.out.println("insert処理をするテスト開始");
		Comment comment  = new Comment();
		comment.setId(6);
		comment.setName("池原");
		comment.setContent("池原さん書き込みのコメント1");
		comment.setArticleId(3);
		
		commentService.insert(comment);
		List<Comment> commentList = commentService.findByArticleId(2);
		
		assertThat("件数が一致しません", commentList.size(), is(3));
		assertThat("内容が一致しません", commentList.get(2).getContent(), is("池原さん書き込みのコメント1"));
		
		System.out.println("insert処理テスト終了");
	}
	@Test
	public void deleteTest() {
		System.out.println("delete処理をするテスト開始");
		commentService.deleteByArticleId(3);
	}
}
