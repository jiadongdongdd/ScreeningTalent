package com.idsmanager.xsifter.web.controller.question;

import com.idsmanager.xsifter.domain.question.QuestionPaginated;
import com.idsmanager.xsifter.service.QuestionService;
import com.idsmanager.xsifter.service.dto.question.QuestionFormDto;
import com.idsmanager.xsifter.service.dto.question.QuestionModifyFormDto;
import com.idsmanager.xsifter.service.dto.question.QuestionSubmitDto;
import com.idsmanager.xsifter.service.dto.question.TagDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 题目录入
 * Created by LZW on 2016/9/20.
 */
@Controller
@RequestMapping("/admin/question/")
public class AdminQuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("list")
    public String list(QuestionPaginated paginated, Model model) {
        paginated = questionService.loadQuestionPaginated(paginated);
        model.addAttribute("paginated", paginated);
        return "admin/question/admin_question_list";
    }

    @RequestMapping(value = "form/create", method = RequestMethod.GET)
    public String create(Model model) {
        QuestionFormDto formDto = questionService.loadQuestionFormDto();
        model.addAttribute("formDto", formDto);
        return "admin/question/admin_question_form";
    }

    @RequestMapping(value = "form/create", method = RequestMethod.POST)
    @ResponseBody
    public QuestionSubmitDto create(@ModelAttribute("formDto") @Valid QuestionFormDto formDto, BindingResult result, Model model) {
        return questionService.createQuestion(formDto);
    }

    @RequestMapping(value = "load/first_level_tag", method = RequestMethod.GET)
    public String loadFirstLevelTagData(Model model) {
        TagDataDto dto = questionService.loadFirstLevelTagDataDto();
        model.addAttribute("dto", dto);
        return "admin/question/admin_tag_data_form";
    }

    @RequestMapping(value = "load/other_level_tag_{id}", method = RequestMethod.GET)
    @ResponseBody
    public TagDataDto loadOtherTagData(@PathVariable("id") String id) {
        TagDataDto dto = questionService.loadOtherTagDataDto(id);
        return dto;
    }

    @RequestMapping(value = "remove_{uuid}", method = RequestMethod.GET)
    public String remove(@PathVariable("uuid") String uuid) {
        questionService.removeQuestion(uuid);
        return "redirect:list";
    }

    @RequestMapping(value = "form/edit_{uuid}", method = RequestMethod.GET)
    public String edit(@PathVariable("uuid") String uuid, Model model) {
        QuestionModifyFormDto formDto = questionService.loadQuestionModifyFormDto(uuid);
        model.addAttribute("formDto", formDto);
        return "admin/question/admin_question_edit_form";
    }

    @RequestMapping(value = "form/edit_{uuid}", method = RequestMethod.POST)
    @ResponseBody
    public QuestionSubmitDto edit(@ModelAttribute("formDto") @Valid QuestionModifyFormDto formDto, BindingResult result) {
        return questionService.editQuestion(formDto);
    }


}
