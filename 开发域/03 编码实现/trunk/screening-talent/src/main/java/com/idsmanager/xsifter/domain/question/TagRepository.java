package com.idsmanager.xsifter.domain.question;

import java.util.List;
import java.util.Map;

/**
 * Created by LZW on 2016/9/18.
 */
public interface TagRepository {

    Tag findTagById(String id);

    List<Tag> findSubTagsByPId(String pId);

    List<Tag> findAllTags();

    void saveTag(Tag tag);
}
