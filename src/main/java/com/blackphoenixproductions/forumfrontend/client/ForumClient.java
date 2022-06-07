package com.blackphoenixproductions.forumfrontend.client;

import com.blackphoenixproductions.forumfrontend.dto.Filter;
import com.blackphoenixproductions.forumfrontend.dto.NotificationDTO;
import com.blackphoenixproductions.forumfrontend.dto.post.EditPostDTO;
import com.blackphoenixproductions.forumfrontend.dto.post.InsertPostDTO;
import com.blackphoenixproductions.forumfrontend.dto.post.PostDTO;
import com.blackphoenixproductions.forumfrontend.dto.topic.EditTopicDTO;
import com.blackphoenixproductions.forumfrontend.dto.topic.InsertTopicDTO;
import com.blackphoenixproductions.forumfrontend.dto.topic.TopicDTO;
import com.blackphoenixproductions.forumfrontend.dto.topic.VTopicDTO;
import com.blackphoenixproductions.forumfrontend.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@FeignClient(value = "forum-client", path = "/api", url = "${backend.path}")
public interface ForumClient {

    String AUTH_TOKEN = "Authorization";

    @GetMapping(value = "/getTotalUsers")
    ResponseEntity<Long> getTotalUsers();

    @GetMapping(value = "/getTotalTopics")
    ResponseEntity<Long> getTotalTopics();

    @GetMapping(value = "/getTotalPosts")
    ResponseEntity<Long> getTotalPosts();

    @PostMapping(value = "/findFilteredTopicsByPage")
    ResponseEntity<PagedModel<EntityModel<VTopicDTO>>> findFilteredTopicsByPage(@RequestParam int page, @RequestParam int size,
                                                                                 @RequestBody(required = false) Filter filter);
    @PostMapping(value = "/findPostsByPage")
    ResponseEntity<PagedModel<EntityModel<PostDTO>>> findPostsByPage(@RequestParam Long topicId, @RequestParam int page, @RequestParam int size);

    @PostMapping(value = "createPost")
    ResponseEntity<EntityModel<PostDTO>> createPost(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestBody InsertPostDTO postDTO);

    @PostMapping(value = "editPost")
    ResponseEntity<EntityModel<PostDTO>> editPost(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestBody EditPostDTO postDTO);

    @GetMapping(value = "/findTopic")
    ResponseEntity<EntityModel<TopicDTO>> findTopic(@RequestParam Long id);

    @PostMapping(value = "createTopic")
    ResponseEntity<EntityModel<TopicDTO>> createTopic(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestBody InsertTopicDTO insertTopicDTO);

    @PostMapping(value = "editTopic")
    ResponseEntity<EntityModel<TopicDTO>> editTopic(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestBody EditTopicDTO topicDTO);

    @GetMapping (value = "/retriveUser")
    ResponseEntity<EntityModel<UserDTO>> retriveUser(@RequestHeader(AUTH_TOKEN) String bearerToken);

    @PostMapping (value = "/changeUserUsername")
    ResponseEntity<EntityModel<UserDTO>> changeUserUsername(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestParam String newUsername);

    @GetMapping(value = "/getBuildVersionBackEnd")
    ResponseEntity<String> getBuildVersionBackEnd();

    @GetMapping(value = "getUserNotificationList")
    ResponseEntity<CollectionModel<NotificationDTO>> getUserNotificationList(@RequestHeader(AUTH_TOKEN) String bearerToken);

    @GetMapping(value = "getUserNotificationStatus")
    ResponseEntity<Boolean> getUserNotificationStatus(@RequestHeader(AUTH_TOKEN) String bearerToken);

    @GetMapping(value = "setReadedNotificationStatus")
    ResponseEntity<String> setReadedNotificationStatus(@RequestHeader(AUTH_TOKEN) String bearerToken);


}
