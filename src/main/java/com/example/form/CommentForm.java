package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Commentのフォームクラス.
 * 
 * @author sho.ikehara
 *
 */
public class CommentForm {
	/**コメント者名*/
	@NotBlank(message="名前は必須です")
	private String name;
	/**コメント内容*/
	@Size(min=1,max=127,message="コメントは1文字以上127文字以下で入力してください")
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
