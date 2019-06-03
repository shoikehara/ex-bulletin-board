package com.example.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Article;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceTest {
	@Autowired
	private ArticleService articleService;
	
	@Test
	public void findAllTest() {
		System.out.println("全件検索するテスト開始");
		
		List<Article> articleList = articleService.findAll();
		
		assertThat("件数が一致しません", articleList.size(), is(2));
		assertThat("最初の内容が一致しません", articleList.get(0).getContent(), is("この掲示板について"));
		assertThat("最後の内容が一致しません", articleList.get(1).getContent(), is("新たな投稿です。"));
		
		System.out.println("全件検索するテスト終了");
	}
	@Test
	public void insertTest() {
		System.out.println("insert処理をするテスト開始");
		Article article  = new Article();
		article.setId(3);
		article.setName("田中");
		article.setContent("二番目の投稿です。");
		
		articleService.insert(article);
		List<Article> articleList = articleService.findAll();
		
		assertThat("件数が一致しません", articleList.size(), is(3));
		assertThat("最初の内容が一致しません", articleList.get(0).getContent(), is("この掲示板について"));
		assertThat("最後の内容が一致しません", articleList.get(2).getContent(), is("二番目の投稿です。"));
		
		System.out.println("insert処理テスト終了");
	}
	@Test
	public void deleteTest() {
		System.out.println("delete処理をするテスト開始");
		
		articleService.delete(3);
		List<Article> articleList = articleService.findAll();
		
		assertThat("件数が一致しません", articleList.size(), is(2));
		assertThat("最初の内容が一致しません", articleList.get(0).getContent(), is("この掲示板について"));
		assertThat("最後の内容が一致しません", articleList.get(1).getContent(), is("新たな投稿です。"));
		
		System.out.println("delete処理テスト終了");
	}
}
