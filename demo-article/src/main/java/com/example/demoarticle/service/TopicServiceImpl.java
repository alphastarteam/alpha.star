package com.example.demoarticle.service;


import com.example.demoarticle.dataaccess.model.Reply;
import com.example.demoarticle.dataaccess.model.Topic;
import com.example.demoarticle.dataaccess.model.TopicCategory;
import com.example.demoarticle.dataaccess.repository.ReplyRepository;
import com.example.demoarticle.dataaccess.repository.TopicCategoryRepository;
import com.example.demoarticle.dataaccess.repository.TopicRepository;
import com.example.demoarticle.model.GetReplies;
import com.example.demoarticle.model.GetTopics;
import com.example.demoarticle.model.PublishTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    TopicCategoryRepository topicCategoryRepository;

    @Autowired
    ReplyRepository replyRepository;

    @Override
    public Topic publish(Long userId, PublishTopic topic) {
        var newTopic = new Topic();
        newTopic.setCategoryId(topic.getCategoryId());
        newTopic.setContent(topic.getContent());
        newTopic.setReward(topic.getReward());
        newTopic.setTitle(topic.getTitle());
        newTopic.setUserId(userId);
        return topicRepository.saveAndFlush(newTopic);
    }

    @Override
    public List<TopicCategory> getCategories() {
        return topicCategoryRepository.findAll().stream().sorted(Comparator.comparing(TopicCategory::getId)).collect(Collectors.toList());
    }

    @Override
    public Topic getTopic(Long id) {
        return topicRepository.findById(id).orElse(null);

    }

    @Override
    public TopicCategory getCategory(int categoryId) {
        return topicCategoryRepository.findById(Long.valueOf(categoryId)).orElse(null);

    }

    @Override
    public Page<Topic> getTopics(GetTopics getTopics) {
        var topics = topicRepository.findAll((Specification<Topic>) (root, criteriaQuery, criteriaBuilder) -> {
            var list = new ArrayList<Predicate>();
            if (getTopics.getComplete() != null) {
                list.add(criteriaBuilder.equal(root.get("isComplete").as(Boolean.class), getTopics.getComplete()));
            }
            if (getTopics.getCategoryId() != null) {
                list.add(criteriaBuilder.equal(root.get("categoryId").as(Long.class), getTopics.getCategoryId()));
            }
            if (getTopics.getEssence() != null) {
                list.add(criteriaBuilder.equal(root.get("isEssence").as(Boolean.class), getTopics.getEssence()));
            }
            if (getTopics.getTop() != null) {
                list.add(criteriaBuilder.equal(root.get("isTop").as(Boolean.class), getTopics.getTop()));
            }
            var p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        }, PageRequest.of(getTopics.getPage(), getTopics.getLimit(), new Sort(Sort.Direction.DESC, "id")));
        return topics;
    }

    @Override
    public Page<Reply> getReplies(GetReplies getReplies) {
        return replyRepository.findRepliesByTopicId(getReplies.getTopicId(), PageRequest.of(getReplies.getPage(), getReplies.getLimit()));
    }

    @Override
    @Transactional
    public Reply publishReply(long topicId, String content, long id) {
        var reply = new Reply();
        reply.setContent(content);
        reply.setTopicId(topicId);
        reply.setUserId(id);
        reply.setCreationTime(new Timestamp(System.currentTimeMillis()));
        reply.setLikeCount(0);
        topicRepository.IncreaseReplyCount(topicId);
        return replyRepository.saveAndFlush(reply);

    }

    @Override
    public List<Topic> getTopTopics() {
        return topicRepository.findAll(PageRequest.of(0, 100, new Sort(Sort.Direction.DESC, "replyCount"))).getContent();
    }

    @Override
    public void increasePopularity(long topicId) {
        topicRepository.increasePopularity(topicId);
    }

    @Override
    public void top(Long id) {
        var topic = topicRepository.findById(id).orElse(null);
        if (topic == null) {
            return;
        }
        topicRepository.setTop(id, !topic.isTop());
    }

    @Override
    public void essence(Long id) {
        var topic = topicRepository.findById(id).orElse(null);
        if (topic == null) {
            return;
        }
        topicRepository.setEssence(id, !topic.isEssence());
    }

    @Override
    public List<Reply> getWeekReplies() {
        var cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.add(Calendar.DAY_OF_MONTH, -7);
        var time = new Timestamp(cal.getTimeInMillis());
        return replyRepository.findByCreationTimeGreaterThanEqual(time);
    }
}
