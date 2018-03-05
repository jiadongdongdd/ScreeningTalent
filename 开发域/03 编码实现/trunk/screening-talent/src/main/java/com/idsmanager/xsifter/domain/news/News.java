package com.idsmanager.xsifter.domain.news;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.xsifter.domain.AbstractDomain;

@Document(collection = "News")
public class News extends AbstractDomain {

	private static final long serialVersionUID = 333353041546167077L;

	private String title;
	private String content;
	private String picture;
	private Date dateTime;
	private Category category;
	private Platform platform;
	private Boolean ispush = Boolean.FALSE;

	public News() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public News setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getContent() {
		return content;
	}

	public News setContent(String content) {
		this.content = content;
		return this;
	}

	public String getPicture() {
		return picture;
	}

	public News setPicture(String picture) {
		this.picture = picture;
		return this;
	}

	public String getDateTime() {
		return DateUtils.toDateText(dateTime,"yyyy-MM-dd");
	}

	public News setDateTime(Date dateTime) {
		this.dateTime = dateTime;
		return this;
	}

	public Category getCategory() {
		return category;
	}

	public News setCategory(Category category) {
		this.category = category;
		return this;
	}

	public Boolean getIspush() {
		return ispush;
	}

	public News setIspush(Boolean ispush) {
		this.ispush = ispush;
		return this;
	}

	public Platform getPlatform() {
		return platform;
	}

	public News setPlatform(Platform platform) {
		this.platform = platform;
		return this;
	}

}
