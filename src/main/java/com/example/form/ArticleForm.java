package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Articleのフォームクラス.
 * 
 * @author sho.ikehara
 *
 */
public class ArticleForm {
	/**投稿者名*/
	@NotBlank(message="名前は必須です")
	private String name;
	/**投稿内容*/
	@Size(min=1,max=127,message="投稿内容は1文字以上127文字以下で入力してください")
	private String content;
	
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
}
