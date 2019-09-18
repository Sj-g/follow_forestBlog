package com.it.service.Imp;

import com.it.entity.Tag;
import com.it.mapper.TagMapper;
import com.it.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ITagService implements TagService {
    @Autowired
    private TagMapper tagMapper;
    @Override
    public List<Tag> tagList() {
        return tagMapper.listTag();
    }
}
