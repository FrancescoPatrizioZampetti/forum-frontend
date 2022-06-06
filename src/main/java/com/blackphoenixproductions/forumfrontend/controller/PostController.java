package com.blackphoenixproductions.forumfrontend.controller;



import com.blackphoenixproductions.forumfrontend.dto.post.EditPostDTO;
import com.blackphoenixproductions.forumfrontend.dto.post.InsertPostDTO;
import com.blackphoenixproductions.forumfrontend.utility.ValidationUtility;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PostController {


    @Autowired
    public PostController() {
    }


    @PostMapping(value = "/createPost")
    public String createPost(@ModelAttribute InsertPostDTO postDTO, @RequestParam Long topicId, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
//        String newAccessToken = tokenUtility.refreshToken(httpServletRequest, httpServletResponse);
//        SimpleUserDTO simpleUserDTO = backendCaller.getLoggedUser(newAccessToken);
        String sanitizedMessage = Jsoup.clean(postDTO.getMessage(), Whitelist.relaxed().addTags("p").addAttributes(":all", "style"));
        postDTO.setMessage(sanitizedMessage);
        if(ValidationUtility.isValidPostInput(postDTO.getMessage())){
            postDTO.setTopicId(topicId);
//            PostDTO createdPost = backendCaller.createPost(postDTO, newAccessToken);
//            PagedModel<EntityModel<PostDTO>> postPageDTO = backendCaller.getPostsByPage(topicId,0L,10L);
//            return "redirect:/viewtopic?id=" + createdPost.getTopic().getId() + "&page=" + (postPageDTO.getMetadata().getTotalPages()-1);
            return "";
        }
        else{
            return "redirect:/forum";
        }
    }


    @PostMapping(value = "/editPost")
    public String editPost(@ModelAttribute EditPostDTO postDTO, @RequestParam Long topicId, @RequestParam Long pageNumber, @RequestParam Long postId,
                           HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
//        String newAccessToken = tokenUtility.refreshToken(httpServletRequest, httpServletResponse);
        String sanitizedMessage = Jsoup.clean(postDTO.getMessage(), Whitelist.relaxed().addTags("p").addAttributes(":all", "style"));
        postDTO.setMessage(sanitizedMessage);
        postDTO.setId(postId);
        if(ValidationUtility.isValidPostInput(postDTO.getMessage())){
//            backendCaller.editPost(postDTO, newAccessToken);
        }
        return "redirect:/viewtopic?id=" + topicId + "&page=" + pageNumber;
    }





}
