package com.idsmanager.xsifter.service.impl;

import com.idsmanager.commons.utils.paginated.PaginatedLoader;
import com.idsmanager.xsifter.domain.position.MemberPosition;
import com.idsmanager.xsifter.domain.position.MemberPositionRepository;
import com.idsmanager.xsifter.domain.question.*;
import com.idsmanager.xsifter.service.QuestionService;
import com.idsmanager.xsifter.service.business.question.QuestionCreator;
import com.idsmanager.xsifter.service.business.question.QuestionEditor;
import com.idsmanager.xsifter.service.business.question.QuestionModifyFormDtoLoader;
import com.idsmanager.xsifter.service.business.question.QuestionRemover;
import com.idsmanager.xsifter.service.dto.position.PositionDto;
import com.idsmanager.xsifter.service.dto.question.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by LZW on 2016/9/20.
 */
@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MemberPositionRepository memberPositionRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public QuestionPaginated loadQuestionPaginated(QuestionPaginated paginated) {
        final Map<String, Object> map = paginated.queryMap();
        return paginated.load(new PaginatedLoader<QuestionDto>() {
            @Override
            public List<QuestionDto> loadList() {
                List<Question> list = questionRepository.findQuestionPaginatedList(map);
                return QuestionDto.toDtos(list);
            }

            @Override
            public long loadTotalSize() {
                return questionRepository.totalQuestionPaginatedList(map);
            }
        });
    }

    @Override
    public QuestionFormDto loadQuestionFormDto() {
        QuestionFormDto formDto = new QuestionFormDto();
        List<MemberPosition> list = memberPositionRepository.findPositionList();
        formDto.setPositions(PositionDto.toDtos(list));
        return formDto;
    }

    @Override
    public TagDataDto loadFirstLevelTagDataDto() {
        TagDataDto dto = new TagDataDto();
        List<Tag> tags = tagRepository.findSubTagsByPId("2");
        dto.setpId("2");
        dto.setpName("综合标签");
        dto.setTags(TagDto.toDtos(tags));
        return dto;
    }

    @Override
    public TagDataDto loadOtherTagDataDto(String pId) {
        TagDataDto dto = new TagDataDto();
        Tag tag = tagRepository.findTagById(pId);
        List<Tag> tags = tagRepository.findSubTagsByPId(pId);
        dto.setpId(pId);
        dto.setpName(tag.name());
        dto.setTags(TagDto.toDtos(tags));
        return dto;
    }

    @Override
    public QuestionSubmitDto createQuestion(QuestionFormDto formDto) {
        QuestionCreator creator = new QuestionCreator(formDto);
        return creator.create();
    }

    @Override
    public void removeQuestion(String uuid) {
        QuestionRemover remover = new QuestionRemover(uuid);
        remover.remove();
    }

    @Override
    public QuestionModifyFormDto loadQuestionModifyFormDto(String uuid) {
        QuestionModifyFormDtoLoader loader = new QuestionModifyFormDtoLoader(uuid);
        return loader.load();
    }

    @Override
    public QuestionSubmitDto editQuestion(QuestionModifyFormDto formDto) {
        QuestionEditor editor = new QuestionEditor(formDto);
        return editor.edit();
    }
}
