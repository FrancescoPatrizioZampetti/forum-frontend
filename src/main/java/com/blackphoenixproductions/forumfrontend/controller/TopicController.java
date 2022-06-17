package com.blackphoenixproductions.forumfrontend.controller;


import com.blackphoenixproductions.forumfrontend.client.ForumClient;
import com.blackphoenixproductions.forumfrontend.dto.topic.EditTopicDTO;
import com.blackphoenixproductions.forumfrontend.dto.topic.InsertTopicDTO;
import com.blackphoenixproductions.forumfrontend.dto.topic.TopicDTO;
import com.blackphoenixproductions.forumfrontend.security.KeycloakUtility;
import com.blackphoenixproductions.forumfrontend.utility.ValidationUtility;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class TopicController {

    private final ForumClient forumClient;

    @Autowired
    public TopicController(ForumClient forumClient) {
        this.forumClient = forumClient;
    }

    @PostMapping(value = "/createTopic")
    public String createTopic(@ModelAttribute InsertTopicDTO topicDTO,
                              Principal principal,
                              HttpServletRequest httpServletRequest) throws Exception {
        String sanitizedTitle = Jsoup.clean(topicDTO.getTitle(), Whitelist.none());
        String sanitizedMessage = Jsoup.clean(topicDTO.getMessage(), Whitelist.relaxed().addTags("p").addAttributes(":all", "style"));
        topicDTO.setTitle(sanitizedTitle);
        topicDTO.setMessage(sanitizedMessage);
        if (ValidationUtility.isValidTopicInput(topicDTO.getTitle(), topicDTO.getMessage())) {
            TopicDTO createdTopic = forumClient.createTopic(KeycloakUtility.getBearerTokenString(principal), topicDTO).getBody().getContent();
            return "redirect:/viewtopic?id=" + createdTopic.getId();
        }
        return "redirect:/forum";
    }

    @PostMapping(value = "/editTopic")
    public String editTopic(@ModelAttribute EditTopicDTO topicDTO,
                            @RequestParam Long topicId,
                            @RequestParam Long pageNumber,
                            HttpServletRequest httpServletRequest,
                            Principal principal) throws Exception {
        String sanitizedMessage = Jsoup.clean(topicDTO.getMessage(), Whitelist.relaxed().addTags("p").addAttributes(":all", "style"));
        topicDTO.setMessage(sanitizedMessage);
        topicDTO.setId(topicId);
        if(ValidationUtility.isValidPostInput(topicDTO.getMessage())){
            forumClient.editTopic(KeycloakUtility.getBearerTokenString(principal), topicDTO);
        }
        return "redirect:/viewtopic?id=" + topicId + "&page=" + pageNumber;
    }

}
