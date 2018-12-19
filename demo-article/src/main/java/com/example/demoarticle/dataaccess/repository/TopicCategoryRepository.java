package com.example.demoarticle.dataaccess.repository;

import com.example.demoarticle.dataaccess.model.TopicCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicCategoryRepository extends JpaRepository<TopicCategory,Long> {
}
