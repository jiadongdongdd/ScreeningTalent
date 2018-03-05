package com.idsmanager.xsifter.service.dto.news;

import java.util.HashMap;
import java.util.Map;

import com.idsmanager.commons.utils.paginated.DefaultPaginated;
import com.idsmanager.xsifter.domain.news.News;
import com.idsmanager.xsifter.domain.news.Platform;

public class NewsPaginated extends DefaultPaginated<News> {

	private String title;
	
	private Boolean ispush;
	
	private Platform platform;

	public NewsPaginated() {
		super();
	}

	@Override
	public Map<String, Object> queryMap() {
		final Map<String, Object> map = super.queryMap();
		map.put("title", title);
		map.put("ispush", ispush);
		map.put("platform", platform);
		
		return map;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
