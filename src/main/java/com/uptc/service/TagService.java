package com.uptc.service;

import com.uptc.entity.Tag;

import java.util.List;

public interface TagService {
    Tag getTagById(Long id);
    Tag getTagByName(String name);
    Tag createNewTag(String name);
    Tag increaseTagUseCounter(String name);
    Tag decreaseTagUseCounter(String name);
    List<Tag> getTimelineTags();
    void deleteTagByName(String name);
    void updateTagUseCounter(Tag tag);
}
