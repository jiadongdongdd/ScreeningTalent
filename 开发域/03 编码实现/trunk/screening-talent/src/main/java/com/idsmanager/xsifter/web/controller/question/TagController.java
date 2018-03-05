package com.idsmanager.xsifter.web.controller.question;

import com.idsmanager.xsifter.domain.question.TagPaginated;
import com.idsmanager.xsifter.service.TagService;
import com.idsmanager.xsifter.service.business.question.TagTreeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * 标签设置
 * Created by LZW on 2016/9/18.
 */
@Controller
@RequestMapping("/admin/tag/")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "tree")
    public String tagsTree(TagPaginated paginated, Model model) {
        paginated = tagService.loadTagPaginated(paginated);
        model.addAttribute("paginated", paginated);
        return "admin/question/question_tag_tree";
    }

    @RequestMapping(value = "tree_data", method = RequestMethod.GET)
    @ResponseBody
    public TagTreeResult loadTree() {
        return tagService.loadTagTree();
    }

    @RequestMapping(value = "initial_tags", method = RequestMethod.POST)
    public String initialTags() throws IOException {
        tagService.initialTags();
        return "redirect:tree";
    }


}
