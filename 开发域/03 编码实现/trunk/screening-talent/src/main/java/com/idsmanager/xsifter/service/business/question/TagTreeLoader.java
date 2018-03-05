package com.idsmanager.xsifter.service.business.question;

import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.question.Tag;
import com.idsmanager.xsifter.domain.question.TagRepository;
import com.idsmanager.xsifter.service.dto.question.TagDto;

import java.util.List;

/**
 * Created by LZW on 2016/9/18.
 */
public class TagTreeLoader {

    private transient TagRepository tagRepository = BeanProvider.getBean(TagRepository.class);

    public TagTreeResult load() {
        TagTreeResult result = new TagTreeResult();
        final List<Tag> tags = tagRepository.findAllTags();

        if (null == tags || tags.isEmpty()) {
            result.setEmpty(true);
        } else {
            result.setEmpty(false);
            result.setData(TagDto.toDtos(tags));
        }
        return result;
    }
}
