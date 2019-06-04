package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

/**
 * 記事とコメントの一覧を表示するリポジトリクラス.
 * 
 * @author sho.ikehara
 *
 */
@Repository
public class ArticleCommentRepository {
	/**記事とコメントのResultSetExtructor*/
	public static final ResultSetExtractor<List<Article>> ARTICLE_RESULT_EXTRACTOR = rs->{
		List<Article> articleList = new ArrayList<>();
		ArrayList<Comment> commentList = null;
		Integer oldId = 0;
		while(rs.next()) {
			if(rs.getInt("art_id") != oldId) {
				Article article = new Article();
				article.setId(rs.getInt("art_id"));
				article.setName(rs.getString("art_name"));
				article.setContent(rs.getString("art_content"));
				commentList = new ArrayList<>();
				article.setCommentList(commentList);
				articleList.add(article);
			}
			if(rs.getInt("com_id") != 0) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("com_id"));
				comment.setName(rs.getString("com_name"));
				comment.setContent(rs.getString("com_content"));
				comment.setArticleId(rs.getInt("com_article_id"));
				commentList.add(comment);
//				articleList.get(0).getCommentList().add(0,comment);
			}
			oldId = rs.getInt("art_id");
		}
		return articleList;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 結合したテーブルから一覧の情報を取得.
	 * 
	 * @return 記事とコメントの一覧
	 */
	public List<Article> findAll(){
		String sql = "select a.id as art_id,a.name as art_name,a.content as art_content,"
				+ "c.id as com_id,c.name as com_name,c.content as com_content,c.article_id as com_article_id"
				+ " from articles as a left outer join comments as c on a.id=c.article_id order by a.id desc ,c.id desc";
		List<Article> articleList = template.query(sql,ARTICLE_RESULT_EXTRACTOR);
		return articleList;
	}
	
	public void insert(Article article) {
		String sql = "insert into articles(name,content) values(:name,:content)";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("name",article.getName())
				.addValue("content",article.getContent());
		template.update(sql, param);
	}
	
	public void insert(Comment comment) {
		String sql = "insert into comments(name,content,article_id) values(:name,:content,:articleId)";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("name",comment.getName())
				.addValue("content",comment.getContent())
				.addValue("articleId",comment.getArticleId());
		template.update(sql, param);
	}
	
	public void delete(Integer articleId) {
		String sql = "delete from comments where article_id=:articleId;"
				+ "delete from articles where id = :articleId;";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("articleId",articleId)
				.addValue("articleId",articleId);
		template.update(sql, param);
	}
}
