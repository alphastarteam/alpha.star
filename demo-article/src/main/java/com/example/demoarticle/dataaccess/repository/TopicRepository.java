package com.example.demoarticle.dataaccess.repository;

import com.example.demoarticle.dataaccess.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TopicRepository extends JpaRepository<Topic, Long>, JpaSpecificationExecutor<Topic> {

    @Modifying
    @Transactional
    @Query("update Topic set replyCount = replyCount + 1 where id = ?1")
    void IncreaseReplyCount(long topicId);

    @Modifying
    @Transactional
    @Query("update Topic set popularity = popularity + 1,updateTime = now() where id = ?1")
    void increasePopularity(long topicId);

    @Modifying
    @Transactional
    @Query("update Topic set isTop = ?2, updateTime = now() where id = ?1")
    void setTop(Long id, boolean isTop);

    @Modifying
    @Transactional
    @Query("update Topic set isEssence = ?2, updateTime = now() where id = ?1")
    void setEssence(Long id,boolean isEssence);


}
