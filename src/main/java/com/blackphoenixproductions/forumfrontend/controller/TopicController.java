package com.blackphoenixproductions.forumfrontend.controller;


import com.blackphoenixproductions.forumfrontend.client.ForumClient;
import com.blackphoenixproductions.forumfrontend.dto.topic.EditTopicDTO;
import com.blackphoenixproductions.forumfrontend.dto.topic.InsertTopicDTO;
import com.blackphoenixproductions.forumfrontend.dto.topic.TopicDTO;
import com.blackphoenixproductions.forumfrontend.security.KeycloakUtility;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class TopicController {

    private final ForumClient forumClient;

    @Autowired
    public TopicController(ForumClient forumClient) {
        this.forumClient = forumClient;
    }

    @PostMapping(value = "/createTopic")
    public String createTopic(@ModelAttribute @Valid InsertTopicDTO topicDTO,
                              Principal principal) {
        String sanitizedTitle = Jsoup.clean(topicDTO.getTitle(), Safelist.none());
        String sanitizedMessage = Jsoup.clean(topicDTO.getMessage(), Safelist.relaxed().addTags("p").addAttributes(":all", "style"));
        topicDTO.setTitle(sanitizedTitle);
        topicDTO.setMessage(sanitizedMessage);
        TopicDTO createdTopic = forumClient.createTopic(KeycloakUtility.getBearerTokenString(principal), topicDTO).getBody().getContent();
        return "redirect:/viewtopic?id=" + createdTopic.getId();
    }

    @PostMapping(value = "/editTopic")
    public String editTopic(@ModelAttribute @Valid EditTopicDTO topicDTO,
                            @RequestParam Long topicId,
                            @RequestParam Long pageNumber,
                            Principal principal) {
        String sanitizedMessage = Jsoup.clean(topicDTO.getMessage(), Safelist.relaxed().addTags("p").addAttributes(":all", "style"));
        topicDTO.setMessage(sanitizedMessage);
        topicDTO.setId(topicId);
        forumClient.editTopic(KeycloakUtility.getBearerTokenString(principal), topicDTO);
        return "redirect:/viewtopic?id=" + topicId + "&page=" + pageNumber;
    }

}
