package com.example.demoarticle.controller;

import com.example.demoarticle.dataaccess.model.Reply;
import com.example.demoarticle.dataaccess.model.Topic;
import com.example.demoarticle.dataaccess.model.TopicCategory;
import com.example.demoarticle.model.*;
import com.example.demoarticle.service.TopicService;
import com.example.serviceclient.model.FindByIds;
import com.example.serviceclient.model.FindByUserId;
import com.example.serviceclient.model.User;
import model.PageResponse;
import model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hcmony on 2017/9/5.
 */
@RestController
public class TopicController extends BaseController {

    @Autowired
    private TopicService topicService;

    @RequestMapping("/topic/publish")
    public Response publish(@RequestBody PublishTopic topic, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        var newTopic = topicService.publish(getUser().getId(), topic);
        return Success("发表成功", newTopic.getId());
    }

    @RequestMapping("/topic/getCategories")
    public Response<List<TopicCategoryModel>> getCategories() {
        var categories = topicService.getCategories();
        var topicCategories = categories.stream().map(c -> {
            var r = new TopicCategoryModel();
            r.setId(c.getId());
            r.setName(c.getName());
            r.setNavigation(c.isNavigation());
            return r;
        }).collect(Collectors.toList());
        return Success("操作成功", topicCategories);
    }

    @RequestMapping(value = "/topic/getTopic", method = RequestMethod.POST)
    public Response<TopicModel> getTopic(@RequestBody GetByTopicId getByTopicId) {
        var topic = topicService.getTopic(getByTopicId.getId());
        var findByUserId = new FindByUserId();
        findByUserId.setUserId(topic.getUserId());
        var user = userService.findById(findByUserId).getContent();
        var category = topicService.getCategory(topic.getCategoryId());
        var topicResult = map(topic, category, user);
        return Success("获取成功", topicResult);
    }

    TopicModel map(Topic topic, TopicCategory topicCategory, User user) {
        if (topic == null) {
            return null;
        }
        var topicResult = new TopicModel();
        topicResult.setCategoryId(topic.getCategoryId());
        topicResult.setContent(topic.getContent());
        topicResult.setId(topic.getId());
        topicResult.setReward(topic.getReward());
        topicResult.setTitle(topic.getTitle());
        topicResult.setUserId(topic.getUserId());
        topicResult.setPopularity(topic.getPopularity());
        if (topicCategory != null) {
            topicResult.setCategoryName(topicCategory.getName());
        }
        topicResult.setComplete(topic.isComplete());
        topicResult.setEssence(topic.isEssence());
        topicResult.setTop(topic.isTop());
        topicResult.setTime(topic.getCreationTime().getTime());
        if (user != null) {
            topicResult.setUsername(user.getUsername());
        }
        topicResult.setReplyCount(topic.getReplyCount());
        return topicResult;
    }

    @RequestMapping(value = "/topic/getTopics", method = RequestMethod.POST)
    public Response<PageResponse<TopicModel>> getTopics(@RequestBody GetTopics getTopics) {
        var pageTopics = topicService.getTopics(getTopics);
        var result = new PageResponse<TopicModel>();
        result.setPageCount(pageTopics.getTotalPages());
        result.setTotalCount(pageTopics.getTotalElements());
        var topics = mapList(pageTopics.getContent());
        result.setData(topics);
        return Success(result);
    }

    private List<TopicModel> mapList(List<Topic> topics) {
        var userIds = topics.stream().map(x -> x.getUserId()).collect(Collectors.toList());
        var findByIds = new FindByIds();
        findByIds.setIds(userIds);
        var users = userService.findByIds(findByIds).getContent();
        var categories = topicService.getCategories();
        var topicsResult = topics.stream().map(x -> {
            var userStream = users.stream();
            var categoryStream = categories.stream();
            var user = userStream.filter(y -> y.getId() == x.getUserId()).findFirst().orElse(null);
            var category = categoryStream.filter(y -> y.getId() == x.getCategoryId()).findFirst().orElse(null);
            var topic = map(x, category, user);
            return topic;
        }).collect(Collectors.toList());
        return topicsResult;
    }

    @RequestMapping(value = "/topic/getReplies", method = RequestMethod.POST)
    public Response<PageResponse<ReplyModel>> getReplies(@RequestBody GetReplies getReplies) {
        var pageReplies = topicService.getReplies(getReplies);
        var result = new PageResponse<ReplyModel>();
        result.setPageCount(pageReplies.getTotalPages());
        result.setTotalCount(pageReplies.getTotalElements());
        var userIds = pageReplies.get().map(x -> x.getUserId()).collect(Collectors.toList());
        var users = getUserByIds(userIds);
        var replies = pageReplies.get().map(x -> {
            var userStream = users.stream();
            var user = userStream.filter(y -> y.getId() == x.getUserId()).findFirst().orElse(new User());
            var replyModel = map(x, user);
            return replyModel;
        }).collect(Collectors.toList());
        result.setData(replies);
        return Success(result);
    }

    private List<User> getUserByIds(List<Long> userIds) {
        var findByIds = new FindByIds();
        findByIds.setIds(userIds);
        var users = userService.findByIds(findByIds).getContent();
        return users;
    }

    private ReplyModel map(Reply x, User user) {
        var replyModel = new ReplyModel();
        replyModel.setContent(x.getContent());
        replyModel.setId(x.getId());
        replyModel.setLikeCount(x.getLikeCount());
        replyModel.setCreationTime(x.getCreationTime().getTime());
        replyModel.setTopicId(x.getTopicId());
        replyModel.setUserId(x.getUserId());
        replyModel.setUsername(user.getUsername());
        return replyModel;
    }

    @RequestMapping(value = "/topic/reply", method = RequestMethod.POST)
    public Response<ReplyModel> publishReply(@RequestBody PublishReply publishReply, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        var reply = topicService.publishReply(publishReply.getTopicId(), publishReply.getContent(), getUser().getId());
        var replyModel = map(reply, getUser());
        return Success(replyModel);
    }

    @RequestMapping(value = "/topic/getTopTopics", method = RequestMethod.POST)
    public Response<List<TopicModel>> getTopTopics() {
        var topics = mapList(topicService.getTopTopics());
        return Success(topics);
    }

    @RequestMapping(value = "/topic/increasePopularity", method = RequestMethod.POST)
    public Response<Object> increasePopularity(@RequestBody IncreasePopularityRequestModel increasePopularityRequestModel, BindingResult bindingResult) {
        topicService.increasePopularity(increasePopularityRequestModel.getTopicId());
        return Success();
    }

    @RequestMapping(value = "/topic/top", method = RequestMethod.POST)
    public Response<Object> top(@RequestBody TopRequestModel topRequestModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        topicService.top(topRequestModel.getId());
        return Success();
    }

    @RequestMapping(value = "/topic/essence", method = RequestMethod.POST)
    public Response<Object> essence(@RequestBody TopRequestModel topRequestModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        topicService.essence(topRequestModel.getId());
        return Success();
    }


    /**
     * 获取回贴周榜
     *
     * @return
     */
    @RequestMapping(value = "/topic/getReplyWeekRanking", method = RequestMethod.POST)
    public Response<List<ReplyUserRanking>> getReplyWeekRanking() {
        var replies = topicService.getWeekReplies();
        var replyGroups = replies.stream().collect(Collectors.groupingBy(Reply::getUserId, Collectors.counting()));
        List<ReplyUserRanking> replyUserRankings = new ArrayList<>();
        List<ReplyUserRanking> finalReplyUserRankings = replyUserRankings;
        replyGroups.forEach((aLong, aLong2) -> {
            var replyUserRanking = new ReplyUserRanking();
            replyUserRanking.setReplyCount(aLong2);
            replyUserRanking.setUserId(aLong);
            finalReplyUserRankings.add(replyUserRanking);
        });
        List<ReplyUserRanking> toSort = new ArrayList<>();
        for (ReplyUserRanking userRanking : replyUserRankings) {
            toSort.add(userRanking);
        }
        toSort.sort(Comparator.reverseOrder());
        List<ReplyUserRanking> list = new ArrayList<>();
        long limit = 50;
        for (ReplyUserRanking userRanking : toSort) {
            if (limit-- == 0) break;
            list.add(userRanking);
        }
        replyUserRankings = list;
        var userIds = replyUserRankings.stream().map(x -> x.getUserId()).collect(Collectors.toList());
        var users = getUserByIds(userIds);
        replyUserRankings.forEach(replyUserRanking -> {
            var userStream = users.stream();
            replyUserRanking.setUsername(userStream.filter(y -> y.getId() == replyUserRanking.getUserId()).findFirst().orElse(new User()).getUsername());
        });
        return Success(replyUserRankings);
    }
}
