package com.blackphoenixproductions.forumfrontend.client;

import com.blackphoenixproductions.forumfrontend.dto.Filter;
import com.blackphoenixproductions.forumfrontend.dto.topic.VTopicDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "forum-client", path = "/api", url = "http://localhost:8083")
public interface ForumClient {

    @GetMapping(value = "/getTotalUsers")
    ResponseEntity<Long> getTotalUsers ();

    @GetMapping(value = "/getTotalTopics")
    ResponseEntity<Long> getTotalTopics ();

    @GetMapping(value = "/getTotalPosts")
    ResponseEntity<Long> getTotalPosts ();

    @PostMapping(value = "/findFilteredTopicsByPage")
    ResponseEntity<PagedModel<EntityModel<VTopicDTO>>> findFilteredTopicsByPage (@RequestParam int page, @RequestParam int size,
                                                                                 @RequestBody(required = false) Filter filter);

}
