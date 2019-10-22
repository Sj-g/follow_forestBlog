package com.it.controller;

import com.it.dto.Mes;
import com.it.entity.Tag;
import com.it.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
        modelAndView.setViewName("admin/tag");
        return modelAndView;
    }

    /**
     * 删除标签
     *
     * @param tagId 文章Id
     * @return 结果
     */
    @RequestMapping(value = "/deleteTag/{tagId}")
    @ResponseBody
    public Mes deleteTag(@PathVariable Integer tagId) {
        tagService.deleteTag(tagId);

        return Mes.success();
    }

    /**
     * 修改tag
     *
     * @param tag 标签
     * @return 结果
     */
    @RequestMapping(value = "/modTag")
    @ResponseBody
    public Mes modTag(@RequestBody Tag tag) {
        tagService.modTag(tag);
        return Mes.success();
    }

    /**
     * 添加标签
     *
     * @param tag 标签
     * @return 结果
     */
    @RequestMapping("/addTag")
    @ResponseBody
    public Mes addTag(Tag tag) {
        tagService.addTag(tag);
        return Mes.success();
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
    /**
     * 按照标签查找
     */
}
