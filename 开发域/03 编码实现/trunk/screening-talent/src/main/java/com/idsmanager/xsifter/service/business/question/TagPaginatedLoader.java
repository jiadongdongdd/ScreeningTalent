package com.idsmanager.xsifter.service.business.question;

import com.idsmanager.commons.utils.paginated.PaginatedLoader;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.question.Tag;
import com.idsmanager.xsifter.domain.question.TagPaginated;
import com.idsmanager.xsifter.domain.question.TagRepository;
import com.idsmanager.xsifter.service.dto.question.TagDto;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by LZW on 2016/9/18.
 */
public class TagPaginatedLoader {

    private transient TagRepository tagRepository = BeanProvider.getBean(TagRepository.class);

    private TagPaginated paginated;

    public TagPaginatedLoader(TagPaginated paginated) {
        this.paginated = paginated;
    }

    public TagPaginated load() {

        final List<Tag> tags = tagRepository.findAllTags();

        if (null == tags || tags.isEmpty()) {
            paginated.setEmpty(true);
        } else {
            paginated.setEmpty(false);
        }

        return paginated.load(new PaginatedLoader<TagDto>() {
            @Override
            public List<TagDto> loadList() {
                return TagDto.toDtos(tags);
            }

            @Override
            public long loadTotalSize() {
                return tags.size();
            }
        });
    }


}
