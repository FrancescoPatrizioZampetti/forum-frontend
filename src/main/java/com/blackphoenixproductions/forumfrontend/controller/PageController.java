package com.blackphoenixproductions.forumfrontend.controller;

import com.blackphoenixproductions.forumfrontend.client.ForumClient;
import com.blackphoenixproductions.forumfrontend.dto.Filter;
import com.blackphoenixproductions.forumfrontend.dto.NotificationDTO;
import com.blackphoenixproductions.forumfrontend.dto.post.PostDTO;
import com.blackphoenixproductions.forumfrontend.dto.topic.TopicDTO;
import com.blackphoenixproductions.forumfrontend.dto.topic.VTopicDTO;
import com.blackphoenixproductions.forumfrontend.dto.user.SimpleUserDTO;
import com.blackphoenixproductions.forumfrontend.enums.Pagination;
import com.blackphoenixproductions.forumfrontend.security.KeycloakUtility;
import com.blackphoenixproductions.forumfrontend.utility.ValueUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;


@Controller
public class PageController {

    private final ValueUtility valueUtility;
    private final ForumClient forumClient;

    @Autowired
    public PageController(ValueUtility valueUtility, ForumClient forumClient) {
        this.valueUtility = valueUtility;
        this.forumClient = forumClient;
    }

    @GetMapping (value = "/search")
    public String searchPage (Model model, HttpServletRequest req, @RequestParam(required = false) Long page, @RequestParam(required = false) String title,  @RequestParam(required = false) String username) throws Exception {
//        PagedModel<EntityModel<SimpleTopicDTO>> pagedTopics = null;
//        String jwtToken = CookieUtility.getTokenFromCookie(httpServletRequest, CookieUtility.ACCESS_TOKEN_NAME);
        if(page == null){
//            pagedTopics = backendCaller.getTopicsByPage(0L, Pagination.TOPIC_PAGINATION.getValue(), title, username);
        } else{
//            pagedTopics = backendCaller.getTopicsByPage(page, Pagination.TOPIC_PAGINATION.getValue(), title, username);
        }
        setCommonAttributes(model, null);
        model.addAttribute("title", title);
        model.addAttribute("author", username);
//        model.addAttribute("pagedTopics", pagedTopics);
        return "forum-search";
    }

    @GetMapping(value = "/profile")
    public String profilePage(Model model, HttpServletRequest req) throws Exception {
        setCommonAttributes(model, null);
        return "forum-profile";
    }

    @GetMapping (value = {"/forum", "/"})
    public String forumPage(Model model, HttpServletRequest req, Principal principal, @RequestParam(required = false) Long page) throws Exception {
        Integer totalUsers = forumClient.getTotalUsers().getBody().intValue();
        Integer totalTopics = forumClient.getTotalTopics().getBody().intValue();
        Integer totalPosts =  forumClient.getTotalPosts().getBody().intValue();
        PagedModel<EntityModel<VTopicDTO>> pagedTopics = null;
        if (page != null){
            pagedTopics = forumClient.findFilteredTopicsByPage(page.intValue(), Pagination.TOPIC_PAGINATION.getValue(), Filter.builder().build()).getBody();
        } else {
            pagedTopics = forumClient.findFilteredTopicsByPage(0, Pagination.TOPIC_PAGINATION.getValue(), Filter.builder().build()).getBody();
        }
        pagedTopics.getMetadata().getTotalPages();
        setCommonAttributes(model, principal);
        model.addAttribute("totalusers", totalUsers);
        model.addAttribute("totaltopics", totalTopics);
        model.addAttribute("totalposts", totalPosts);
        model.addAttribute("pagedTopics", pagedTopics);
        return "forum";
    }


    @GetMapping (value="/viewtopic")
    public String viewTopic(Model model, HttpServletRequest req, @RequestParam Long id, @RequestParam(required = false) Long page) throws Exception {
        PagedModel<EntityModel<PostDTO>> postPageDTO = null;
//        TopicDTO topicDTO = backendCaller.findTopic(id);
        if(page == null) {
//            postPageDTO = backendCaller.getPostsByPage(id, 0L, Pagination.POST_PAGINATION.getValue());
        }
        else {
//            postPageDTO = backendCaller.getPostsByPage(id, page, Pagination.POST_PAGINATION.getValue());
        }
//        model.addAttribute("topic", topicDTO);
        model.addAttribute("pagedPosts", postPageDTO);
        setCommonAttributes(model, null);
        return "forum-single";
    }

    @GetMapping (value="/notification")
    public String notification(Model model, HttpServletRequest httpServletRequest, Principal principal) {
        List<NotificationDTO> notificationDTOList = (List<NotificationDTO>) forumClient.getUserNotificationList(KeycloakUtility.getBearerTokenString(principal)).getBody();
        model.addAttribute("notificationList", notificationDTOList);
        model.addAttribute("domain", valueUtility.getDomain());
        return "notification :: notification_fragment";
    }

    @GetMapping (value="/readedNotification")
    public @ResponseBody
    void readedNotification (HttpServletRequest httpServletRequest, Principal principal){
        forumClient.setReadedNotificationStatus(KeycloakUtility.getBearerTokenString(principal));
    }


    private void setCommonAttributes (Model model, Principal principal) throws Exception {
        SimpleUserDTO simpleUserDTO = null;
        List<NotificationDTO> notificationDTOList = null;
        Boolean userNotificationStatus = null;
        if(principal != null) {
            simpleUserDTO = forumClient.retriveUser(KeycloakUtility.getBearerTokenString(principal)).getBody().getContent();
            notificationDTOList = (List<NotificationDTO>) forumClient.getUserNotificationList(KeycloakUtility.getBearerTokenString(principal)).getBody();
            userNotificationStatus = forumClient.getUserNotificationStatus(KeycloakUtility.getBearerTokenString(principal)).getBody();
            model.addAttribute("token", KeycloakUtility.getAccessTokenString(principal));
        }
        String buildVersionBE = forumClient.getBuildVersionBackEnd().getBody();
        model.addAttribute("user", simpleUserDTO);
        model.addAttribute("domain", valueUtility.getDomain());
        model.addAttribute("sseBackend", valueUtility.getSseBackend());
        model.addAttribute("notificationList", notificationDTOList);
        model.addAttribute("userNotificationStatus", userNotificationStatus);
        model.addAttribute("buildVersionBE", buildVersionBE);
        model.addAttribute("buildVersionFE", valueUtility.getBuildVersion());
    }


}
