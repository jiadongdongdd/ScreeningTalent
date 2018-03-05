package com.idsmanager.xsifter.web.controller.enterprise;

import com.idsmanager.xsifter.service.EnterpriseExaminationService;
import com.idsmanager.xsifter.service.dto.examination.*;
import com.idsmanager.xsifter.service.dto.question.TagDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by LZW on 2016/9/22.
 */
@Controller
@RequestMapping("/enterprise/exam/")
public class EnterpriseExaminationController {

    @Autowired
    private EnterpriseExaminationService examinationService;

    //综合试卷设置页面
    @RequestMapping(value = "load/{positionUuid}/general_examination_setting_{memberUuid}", method = RequestMethod.GET)
    public String loadGeneralExaminationSetting(@PathVariable("positionUuid") String positionUuid, @PathVariable("memberUuid") String memberUuid, Model model) {
        GeneralExaminationSettingFormDto formDto = examinationService.loadGeneralExaminationSetting(positionUuid, memberUuid);
        model.addAttribute("formDto", formDto);
        return "enterprise/exam/general_examination_setting_form";
    }

    @RequestMapping(value = "load/{positionUuid}/general_examination_setting_{memberUuid}", method = RequestMethod.POST)
    @ResponseBody
    public ExamSettingSubmitDto submitGeneralSetting(@ModelAttribute("formDto") @Valid GeneralExaminationSettingFormDto formDto, BindingResult result) {
        return examinationService.createGeneralSetting(formDto);
    }

    //查询试题数量
    @RequestMapping(value = "query_question_by_tags", method = RequestMethod.POST)
    @ResponseBody
    public QuestionQueryResult queryQuestionByTags(@ModelAttribute("formDto") QuestionQueryTags tags) {
        return examinationService.queryQuestionByTags(tags);
    }

    @RequestMapping(value = "query_question_by_position", method = RequestMethod.POST)
    @ResponseBody
    public QuestionQueryResult questionPositionQueryResult(@ModelAttribute("formDto") QuestionQueryTags tags) {
        return examinationService.queryQuestionByPosition(tags);
    }

    //加载综合/专业题型标签
    @RequestMapping(value = "load_general_tag", method = RequestMethod.GET)
    public String loadGeneralTag(Model model) {
        TagDataDto dto = examinationService.loadGeneralTag();
        model.addAttribute("dto", dto);
        return "enterprise/exam/enterprise_tag_data_form";
    }

    //加载综合/专业标签的其他标签
    @RequestMapping(value = "load_general_other_tag_{id}", method = RequestMethod.GET)
    @ResponseBody
    public TagDataDto loaGeneralOtherTag(@PathVariable("id") String id) {
        TagDataDto dto = examinationService.loadGeneralOtherTagDataDto(id);
        return dto;
    }

    @RequestMapping(value = "load/{positionUuid}/professional_examination_setting_{memberUuid}", method = RequestMethod.GET)
    public String loadProfessionalExaminationSetting(@PathVariable("positionUuid") String positionUuid, @PathVariable("memberUuid") String memberUuid, Model model) {
        ProfessionalExaminationSettingFormDto formDto = examinationService.loadProfessionalExaminationSetting(positionUuid, memberUuid);
        model.addAttribute("formDto", formDto);
        return "enterprise/exam/professional_examination_setting_form";
    }

    @RequestMapping(value = "load_professional_tag", method = RequestMethod.GET)
    public String loadProfessionalTag(Model model) {
        TagDataDto dto = examinationService.loadProfessionalTag();
        model.addAttribute("dto", dto);
        return "enterprise/exam/enterprise_tag_data_form";
    }

    @RequestMapping(value = "load/{positionUuid}/professional_examination_setting_{memberUuid}", method = RequestMethod.POST)
    @ResponseBody
    public ExamSettingSubmitDto submitProfessionalForm(@ModelAttribute("formDto") @Valid ProfessionalExaminationSettingFormDto formDto, BindingResult result) {
        return examinationService.createProfessionalSetting(formDto);
    }

    //综合试卷生成
    @RequestMapping(value = "load_{positionUuid}_general_examination_{memberUuid}", method = RequestMethod.GET)
    public String loadGeneralExamination(@PathVariable("positionUuid") String positionUuid, @PathVariable("memberUuid") String memberUuid, Model model) {
        GeneralExaminationResultFormDto formDto = examinationService.loadGeneralExaminationResultFormDto(positionUuid, memberUuid);
        model.addAttribute("formDto", formDto);
        return "enterprise/exam/general_examination_form";
    }

    @RequestMapping(value = "load_{positionUuid}_general_examination_{memberUuid}", method = RequestMethod.POST)
    public String submitGeneralExamination(@ModelAttribute("formDto") GeneralExaminationResultFormDto formDto) {
        examinationService.createGeneralExaminationResult(formDto);
        return "redirect:load_" + formDto.getPositionUuid() + "_general_examination_result/" + formDto.getMemberUuid();
    }

    @RequestMapping(value = "load_{positionUuid}_general_examination_result/{memberUuid}", method = RequestMethod.GET)
    public String loadGeneralExaminationResult(@PathVariable("positionUuid") String positionUuid, @PathVariable("memberUuid") String memberUuid, Model model) {
        GeneralExaminationResultDto dto = examinationService.loadGeneralExaminationResultDto(memberUuid, positionUuid);
        model.addAttribute("dto", dto);
        return "enterprise/exam/general_examination_result";
    }


}
