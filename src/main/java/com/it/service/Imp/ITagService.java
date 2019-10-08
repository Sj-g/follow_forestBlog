package com.it.service.Imp;

import com.it.entity.Article;
import com.it.entity.ArticleTagRef;
import com.it.entity.Tag;
import com.it.mapper.ArticleTagRefMapper;
import com.it.mapper.TagMapper;
import com.it.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ITagService implements TagService {
    private final TagMapper tagMapper;
    private final ArticleTagRefMapper articleTagRefMapper;

    @Autowired
    public ITagService(TagMapper tagMapper, ArticleTagRefMapper articleTagRefMapper) {
        this.tagMapper = tagMapper;
        this.articleTagRefMapper = articleTagRefMapper;
    }

    @Override
    public List<Tag> tagList() {
        return tagMapper.listTag();
    }

    @Override
    public Integer deleteTag(Integer tagId) {
        return tagMapper.deleteById(tagId);
    }

    @Override
    public Integer modTag(Tag tag) {
        return tagMapper.update(tag);
    }

    @Override
    public Integer addTag(Tag tag) {
        return tagMapper.insert(tag);
    }

    @Override
    public Tag getTag(Integer tagId) {
        return tagMapper.getTagById(tagId);
    }

    @Override
    public Tag getTagByName(String tagName) {
        return tagMapper.getTagByName(tagName);
    }

    @Override
    public List<Tag> getTagByArticleId(Integer article) {
        return articleTagRefMapper.listTagByArticleId(article);
    }
}
