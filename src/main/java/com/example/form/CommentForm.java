package com.example.form;

public class CommentForm {
	/**コメント者名*/
	private String name;
	/**コメント内容*/
	private String content;
	/**記事ID*/
	private String articleId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
}
