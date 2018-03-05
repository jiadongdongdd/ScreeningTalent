package com.idsmanager.xsifter.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idsmanager.xsifter.service.MemberService;
import com.idsmanager.xsifter.service.NewsService;
import com.idsmanager.xsifter.service.dto.menber.MemberPaginated;
import com.idsmanager.xsifter.service.dto.news.NewsFormDto;

@Controller
@RequestMapping("/home/")
public class HomeController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private NewsService newsService;

	@RequestMapping(value = "member/search", method = RequestMethod.GET)
	public String search(MemberPaginated paginated, Model model) {
		paginated = memberService.loadHomeMemberPaginated(paginated);
		model.addAttribute("paginated", paginated);
		return "home/success";
	}

	@RequestMapping(value = "news/detail/{uuid}", method = RequestMethod.GET)
	public String newsSearch(@PathVariable @Valid String uuid, Model model) {
		NewsFormDto dto = newsService.loadMyNewsByUUID(uuid);
		model.addAttribute("dto", dto);
		return "home/news_detail";
	}

}
