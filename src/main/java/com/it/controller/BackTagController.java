package com.it.controller;

import com.it.dto.ResponseVo;
import com.it.entity.Tag;
import com.it.service.TagService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 对分类的增加 删除 修改 查询
 */
@Controller
public class BackTagController {
    private final TagService tagService;

    @Autowired
    public BackTagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * 查询分类的列表
     *
     * @param modelAndView 标签列表
     * @return 标签数据
     */
    @RequestMapping("/tagList")
    public ModelAndView getTagList(ModelAndView modelAndView) {
        List<Tag> tagList = tagService.tagList();
        modelAndView.addObject("tagList", tagList);
        modelAndView.setViewName("");
        return modelAndView;
    }

    /**
     * 删除标签
     *
     * @param tagId 文章Id
     * @return 结果
     */
    @RequestMapping(value = "/deleteTag", method = RequestMethod.DELETE)
    public ResponseVo deleteTag(Integer tagId) {
        tagService.deleteTag(tagId);

        return ResponseVo.success();
    }

    /**
     * 修改tag
     *
     * @param tag 标签
     * @return 结果
     */
    @RequestMapping(value = "/modTag", method = RequestMethod.PUT)
    public ResponseVo modTag(Tag tag) {
        tagService.modTag(tag);

        return ResponseVo.success();
    }

    /**
     * 添加标签
     *
     * @param tag 标签
     * @return 结果
     */
    @RequestMapping("/addTag")
    public ResponseVo addTag(Tag tag) {
        tagService.addTag(tag);

        return ResponseVo.success();
    }

    /**
     * 通过id查询单个
     */
    @RequestMapping("/getTag")
    public ModelAndView getTagById(Integer tagId, ModelAndView modelAndView) {
        Tag tag = tagService.getTag(tagId);
        modelAndView.addObject("tag", tag);
        modelAndView.setViewName("");
        return modelAndView;
    }

    /**
     * 通过名称查询
     *
     * @param tagName      标签名称
     * @param modelAndView 标签
     * @return 视图和数据
     */
    @RequestMapping("/getTagByName")
    public ModelAndView getTagByName(String tagName, ModelAndView modelAndView) {
        Tag tag = tagService.getTagByName(tagName);
        modelAndView.addObject("tag", tag);
        modelAndView.setViewName("");
        return modelAndView;
    }
}
