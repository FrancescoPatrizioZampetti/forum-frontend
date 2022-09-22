package com.blackphoenixproductions.forumfrontend.controller;



import com.blackphoenixproductions.forumfrontend.client.ForumClient;
import com.blackphoenixproductions.forumfrontend.dto.post.EditPostDTO;
import com.blackphoenixproductions.forumfrontend.dto.post.InsertPostDTO;
import com.blackphoenixproductions.forumfrontend.dto.post.PostDTO;
import com.blackphoenixproductions.forumfrontend.security.KeycloakUtility;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class PostController {

    private final ForumClient forumClient;

    @Autowired
    public PostController(ForumClient forumClient) {
        this.forumClient = forumClient;
    }


    @PostMapping(value = "/createPost")
    public String createPost(@ModelAttribute @Valid InsertPostDTO postDTO,
                             @RequestParam Long topicId,
                             Principal principal,
                             HttpServletRequest httpServletRequest) throws Exception {
        String sanitizedMessage = Jsoup.clean(postDTO.getMessage(), Safelist.relaxed().addTags("p").addAttributes(":all", "style"));
        postDTO.setMessage(sanitizedMessage);
        postDTO.setTopicId(topicId);
        PostDTO createdPost = forumClient.createPost(KeycloakUtility.getBearerTokenString(principal), postDTO).getBody().getContent();
        PagedModel<EntityModel<PostDTO>> postPageDTO = forumClient.findPostsByPage(topicId, 0, 10).getBody();
        return "redirect:/viewtopic?id=" + createdPost.getTopic().getId() + "&page=" + (postPageDTO.getMetadata().getTotalPages() - 1);
    }


    @PostMapping(value = "/editPost")
    public String editPost(@ModelAttribute @Valid EditPostDTO postDTO,
                           @RequestParam Long topicId,
                           @RequestParam Long pageNumber,
                           @RequestParam Long postId,
                           Principal principal,
                           HttpServletRequest httpServletRequest) throws Exception {
        String sanitizedMessage = Jsoup.clean(postDTO.getMessage(), Safelist.relaxed().addTags("p").addAttributes(":all", "style"));
        postDTO.setMessage(sanitizedMessage);
        postDTO.setId(postId);
        forumClient.editPost(KeycloakUtility.getBearerTokenString(principal), postDTO);
        return "redirect:/viewtopic?id=" + topicId + "&page=" + pageNumber;
    }





}
