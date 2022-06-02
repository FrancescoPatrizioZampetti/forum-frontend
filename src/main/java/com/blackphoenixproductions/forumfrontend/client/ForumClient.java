package com.blackphoenixproductions.forumfrontend.client;

import com.blackphoenixproductions.forumfrontend.dto.Filter;
import com.blackphoenixproductions.forumfrontend.dto.topic.VTopicDTO;
import com.blackphoenixproductions.forumfrontend.dto.user.SimpleUserDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@FeignClient(value = "forum-client", path = "/api", url = "${backend.path}")
public interface ForumClient {

    String AUTH_TOKEN = "Authorization";


    @GetMapping(value = "/getTotalUsers")
    ResponseEntity<Long> getTotalUsers ();

    @GetMapping(value = "/getTotalTopics")
    ResponseEntity<Long> getTotalTopics ();

    @GetMapping(value = "/getTotalPosts")
    ResponseEntity<Long> getTotalPosts ();

    @PostMapping(value = "/findFilteredTopicsByPage")
    ResponseEntity<PagedModel<EntityModel<VTopicDTO>>> findFilteredTopicsByPage (@RequestParam int page, @RequestParam int size,
                                                                                 @RequestBody(required = false) Filter filter);
    @GetMapping (value = "/retriveUser")
    ResponseEntity<EntityModel<SimpleUserDTO>> retriveUser (@RequestHeader(AUTH_TOKEN) String bearerToken);
//    ResponseEntity<EntityModel<SimpleUserDTO>> retriveUser ();


}
