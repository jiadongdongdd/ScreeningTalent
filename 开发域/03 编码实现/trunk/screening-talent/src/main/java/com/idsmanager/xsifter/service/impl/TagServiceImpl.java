package com.idsmanager.xsifter.service.impl;

import com.idsmanager.commons.utils.paginated.PaginatedLoader;
import com.idsmanager.xsifter.domain.question.Tag;
import com.idsmanager.xsifter.domain.question.TagPaginated;
import com.idsmanager.xsifter.domain.question.TagRepository;
import com.idsmanager.xsifter.service.TagService;
import com.idsmanager.xsifter.service.business.question.DefaultTagsInitializer;
import com.idsmanager.xsifter.service.business.question.TagPaginatedLoader;
import com.idsmanager.xsifter.service.business.question.TagTreeLoader;
import com.idsmanager.xsifter.service.business.question.TagTreeResult;
import com.idsmanager.xsifter.service.dto.question.TagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by LZW on 2016/9/18.
 */
@Service("tagService")
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public TagPaginated loadTagPaginated(TagPaginated paginated) {
        TagPaginatedLoader loader = new TagPaginatedLoader(paginated);
        return loader.load();
    }

    @Override
    public void initialTags() throws IOException {
        DefaultTagsInitializer initializer = new DefaultTagsInitializer();
        initializer.initial();
    }

    @Override
    public TagTreeResult loadTagTree() {
        TagTreeLoader loader = new TagTreeLoader();
        return loader.load();
    }
}
