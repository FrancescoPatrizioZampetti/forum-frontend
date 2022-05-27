package com.blackphoenixproductions.forumfrontend.client;

import com.blackphoenixproductions.forumfrontend.dto.KeyValueDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(value = "forum-client", path = "/api", url = "http://localhost:8083")
public interface ForumClient {

    @GetMapping(value = "/getTotalUsers")
    ResponseEntity<EntityModel<KeyValueDTO>> getTotalUsers ();

}
