package com.uptc.repository;

import com.uptc.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findTagByName(String name);
    void deleteByName(String name);
}
