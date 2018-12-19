package com.example.demoarticle.service;

import com.example.demoarticle.dataaccess.model.Reply;
import com.example.demoarticle.dataaccess.model.Topic;
import com.example.demoarticle.dataaccess.model.TopicCategory;
import com.example.demoarticle.model.GetReplies;
import com.example.demoarticle.model.GetTopics;
import com.example.demoarticle.model.PublishTopic;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TopicService {
    Topic publish(Long userId,PublishTopic topic);

    List<TopicCategory> getCategories();

    Topic getTopic(Long id);

    TopicCategory getCategory(int categoryId);

    Page<Topic> getTopics(GetTopics getTopics);

    Page<Reply> getReplies(GetReplies getReplies);

    Reply publishReply(long topicId, String content, long id);

    List<Topic> getTopTopics();

    void increasePopularity(long topicId);

    void top(Long id);

    void essence(Long id);

    List<Reply> getWeekReplies();
}
