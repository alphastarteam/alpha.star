package com.example.demoarticle.dataaccess.repository;

import com.example.demoarticle.dataaccess.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic,Long> {

}
