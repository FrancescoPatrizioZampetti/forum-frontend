package com.blackphoenixproductions.forumfrontend.controller;


import com.blackphoenixproductions.forumfrontend.dto.topic.EditTopicDTO;
import com.blackphoenixproductions.forumfrontend.dto.topic.InsertTopicDTO;
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
public class TopicController {

    @Autowired
    public TopicController() {
    }

    @PostMapping(value = "/createTopic")
    public String createTopic(@ModelAttribute InsertTopicDTO topicDTO, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
//        String newAccessToken = tokenUtility.refreshToken(httpServletRequest, httpServletResponse);
//        SimpleUserDTO simpleUserDTO = backendCaller.getLoggedUser(newAccessToken);
        String sanitizedTitle = Jsoup.clean(topicDTO.getTitle(), Whitelist.none());
        String sanitizedMessage = Jsoup.clean(topicDTO.getMessage(), Whitelist.relaxed().addTags("p").addAttributes(":all", "style"));
        topicDTO.setTitle(sanitizedTitle);
        topicDTO.setMessage(sanitizedMessage);
        if(ValidationUtility.isValidTopicInput(topicDTO.getTitle(), topicDTO.getMessage())){
//            SimpleTopicDTO createdTopic = backendCaller.createTopic(topicDTO, newAccessToken);
//            return "redirect:/viewtopic?id=" + createdTopic.getId();
            return "";
        }
        else{
            return "redirect:/forum";
        }
    }

    @PostMapping(value = "/editTopic")
    public String editTopic(@ModelAttribute EditTopicDTO topicDTO, @RequestParam Long topicId, @RequestParam Long pageNumber,
                            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String sanitizedMessage = Jsoup.clean(topicDTO.getMessage(), Whitelist.relaxed().addTags("p").addAttributes(":all", "style"));
        topicDTO.setMessage(sanitizedMessage);
        topicDTO.setId(topicId);
        if(ValidationUtility.isValidPostInput(topicDTO.getMessage())){
//            backendCaller.editTopic(topicDTO, newAccessToken);
        }
        return "redirect:/viewtopic?id=" + topicId + "&page=" + pageNumber;
    }

}
