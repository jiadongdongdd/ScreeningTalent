package com.idsmanager.xsifter.service.dto.news;


import org.springframework.web.multipart.MultipartFile;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.xsifter.domain.news.Category;
import com.idsmanager.xsifter.domain.news.News;
import com.idsmanager.xsifter.domain.news.Platform;
import com.idsmanager.xsifter.service.dto.AbstractDto;

public class NewsFormDto extends AbstractDto {

	private static final long serialVersionUID = 44877961748991756L;

	private String uuid;
	private String title;
	private String content;
	private MultipartFile pictureFile;
	private String pictureGuid;
	private String dateTime;
	private Boolean ispush;
	private Category category;
	private Platform platform;
	public NewsFormDto() {
		super();
	}

	public NewsFormDto(News news) {
		this.uuid = news.getUuid();
		this.title = news.getTitle();
		this.content = news.getContent();
		this.pictureGuid = news.getPicture();
		this.dateTime = news.getDateTime();
		this.category = news.getCategory();
		this.ispush = news.getIspush();
		this.platform = news.getPlatform();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Category[] getCategories() {
		return Category.values();
	}
	
	public Platform[] getPlatforms(){
		return Platform.values();
	}

	public MultipartFile getPictureFile() {
		return pictureFile;
	}

	public void setPictureFile(MultipartFile pictureFile) {
		this.pictureFile = pictureFile;
	}

	public String getPictureGuid() {
		return pictureGuid;
	}

	public void setPictureGuid(String pictureGuid) {
		this.pictureGuid = pictureGuid;
	}

	public News create() {
		return new News().setCategory(category).setContent(content)
				.setTitle(title).setDateTime(DateUtils.getDate(dateTime)).setIspush(ispush).setPlatform(platform);
	}

	public News update(News newsUpdate) {
		return newsUpdate.setCategory(category).setContent(content)
				.setTitle(title).setDateTime(DateUtils.getDate(dateTime)).setIspush(ispush).setPlatform(platform);
	}

	public Boolean getIspush() {
		return ispush;
	}

	public void setIspush(Boolean ispush) {
		this.ispush = ispush;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	
}
