package com.idsmanager.xsifter.service.business.question;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.position.MemberPosition;
import com.idsmanager.xsifter.domain.position.MemberPositionRepository;
import com.idsmanager.xsifter.domain.question.*;
import com.idsmanager.xsifter.service.dto.position.PositionDto;
import com.idsmanager.xsifter.service.dto.question.OptionDto;
import com.idsmanager.xsifter.service.dto.question.QuestionModifyFormDto;
import com.idsmanager.xsifter.service.dto.question.TagDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZW on 2016/9/21.
 */
public class QuestionModifyFormDtoLoader {

    private transient QuestionRepository questionRepository = BeanProvider.getBean(QuestionRepository.class);

    private transient MemberPositionRepository memberPositionRepository = BeanProvider.getBean(MemberPositionRepository.class);

    private transient TagRepository tagRepository = BeanProvider.getBean(TagRepository.class);

    private String uuid;

    public QuestionModifyFormDtoLoader(String uuid) {
        this.uuid = uuid;
    }

    public QuestionModifyFormDto load() {
        Question question = questionRepository.findQuestionByUuid(uuid);

        QuestionModifyFormDto formDto = new QuestionModifyFormDto(question);

        //所有职位
        loadAllPositions(formDto);
        //已选择的标签
        loadSelectedTags(question, formDto);
        //所有选项
        loadAllOptions(question, formDto);


        return formDto;
    }

    private void loadAllOptions(Question question, QuestionModifyFormDto formDto) {
        final QuestionType type = formDto.getType();
        if (!QuestionType.SHORT_ANSWER.equals(type)) {
            int firstLetter = 65;
            final List<String> options = question.options();
            if (null != options && !options.isEmpty()) {
                List<OptionDto> optionDtos = new ArrayList<>(options.size());
                for (int i = 0; i < options.size(); i++) {
                    int letterNum = firstLetter + i;
                    String letter = String.valueOf((char) letterNum);
                    OptionDto optionDto = new OptionDto(letter, options.get(i));
                    optionDtos.add(optionDto);
                }
                formDto.setOptionDtos(optionDtos);
            }
        }

    }

    private void loadSelectedTags(Question question, QuestionModifyFormDto formDto) {
        List<String> tags = question.tags();
        List<TagDto> tagDtos = new ArrayList<>();
        for (String id : tags) {
            Tag tag = tagRepository.findTagById(id);
            tagDtos.add(new TagDto(tag));
        }
        formDto.setSelectedTags(tagDtos);
    }


    private void loadAllPositions(QuestionModifyFormDto formDto) {
        List<MemberPosition> list = memberPositionRepository.findPositionList();
        formDto.setPositions(PositionDto.toDtos(list));
    }
}
