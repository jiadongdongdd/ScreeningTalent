package com.idsmanager.xsifter.web.controller.news;

import javax.validation.Valid;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.idsmanager.xsifter.service.NewsService;
import com.idsmanager.xsifter.service.business.news.MyNewsSaver;
import com.idsmanager.xsifter.service.dto.news.NewsFormDto;
import com.idsmanager.xsifter.service.dto.news.NewsPaginated;

@Controller
@RequestMapping("/admin/news/")
public class NewsController {

	@Autowired
	private NewsService newsService;

	// list page
	@RequestMapping("list")
	public String list(NewsPaginated paginated, Model model) {
		paginated = newsService.loadNewsPaginated(paginated);
		model.addAttribute("paginated", paginated);
		return "news/news_list";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String showCreateForm(Model model) {
		NewsFormDto formDto = new NewsFormDto();
		model.addAttribute("formDto", formDto);
		return "news/news_form";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@ModelAttribute("formDto") @Valid NewsFormDto formDto,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "news/news_form";
		}
		newsService.saveNews(formDto);
		return "redirect:../news/list";
	}

	@RequestMapping(value = "edit/{uuid}", method = RequestMethod.GET)
	public String showEditForm(@PathVariable("uuid") String uuid,
			NewsFormDto formDto, Model model) {
		formDto = newsService.loadMyNewsByUUID(uuid);
		model.addAttribute("plat", formDto.getPlatforms());
		model.addAttribute("formDto", formDto);
		return "news/news_update_form";
	}

	@RequestMapping(value = "edit/{uuid}", method = RequestMethod.POST)
	public String edit(@PathVariable("uuid") String uuid,
			@ModelAttribute("formDto") @Valid NewsFormDto formDto,
			BindingResult result, Model model) {
		formDto.setUuid(uuid);
		if (result.hasErrors()) {
			return "news/news_update_form";
		}
		newsService.saveNews(formDto);
		return "redirect:../list";
	}

	@RequestMapping(value = "delete/{uuid}", method = RequestMethod.GET)
	public String delete(@PathVariable("uuid") String uuid, Model model) {
		newsService.deleteNews(uuid);
		return "redirect:../list";
	}

}
