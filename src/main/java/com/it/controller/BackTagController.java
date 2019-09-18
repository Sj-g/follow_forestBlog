package com.it.controller;

import com.it.entity.Tag;
import com.it.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BackTagController {
    @Autowired
    private TagService tagService;

    @RequestMapping("/tagList")
    public ModelAndView getTagList(ModelAndView modelAndView) {
        List<Tag> tagList = tagService.tagList();
        modelAndView.addObject("tagList", tagList);
        modelAndView.setViewName("");
        return modelAndView;
    }
}
