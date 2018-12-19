package com.example.demoarticle.dataaccess.repository;

import com.example.demoarticle.dataaccess.model.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
    Page<Reply> findRepliesByTopicId(long topicId, Pageable pageable);

    List<Reply> findByCreationTimeGreaterThanEqual(Timestamp timestamp);
}
