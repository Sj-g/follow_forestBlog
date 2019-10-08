package com.it.service;

import com.it.entity.Article;
import com.it.entity.Tag;

import java.util.List;

public interface TagService {
    /**
     * 获取标签
     */
    public List<Tag> tagList();

    /**
     * 删除标签
     * @param tagId 标签Id
     */
    Integer deleteTag(Integer tagId);

    /**
     * 修改标签
     * @param tag 标签
     */
    Integer modTag(Tag tag);

    /**
     * 添加标签
     * @param tag 标签
     */
    Integer addTag(Tag tag);

    /**
     * 获得标签通过Id
     * @param tagId 标签Id
     * @return 标签
     */
    Tag getTag(Integer tagId);

    /**
     * 获得标签通过名字
     * @param tagName 标签名字
     * @return 标签
     */
    Tag getTagByName(String tagName);

    List<Tag> getTagByArticleId(Integer article);
}
