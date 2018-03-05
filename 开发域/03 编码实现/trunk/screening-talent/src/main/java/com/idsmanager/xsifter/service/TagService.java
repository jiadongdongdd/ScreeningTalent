package com.idsmanager.xsifter.service;

import com.idsmanager.xsifter.domain.question.TagPaginated;
import com.idsmanager.xsifter.service.business.question.TagTreeResult;

import java.io.IOException;

/**
 * Created by LZW on 2016/9/18.
 */
public interface TagService {
    TagPaginated loadTagPaginated(TagPaginated paginated);

    void initialTags() throws IOException;

    TagTreeResult loadTagTree();
}
