package com.blackphoenixproductions.forumfrontend.controller;

import com.blackphoenixproductions.forumfrontend.client.ForumClient;
import com.blackphoenixproductions.forumfrontend.dto.Filter;
import com.blackphoenixproductions.forumfrontend.dto.NotificationDTO;
import com.blackphoenixproductions.forumfrontend.dto.post.PostDTO;
import com.blackphoenixproductions.forumfrontend.dto.topic.TopicDTO;
import com.blackphoenixproductions.forumfrontend.dto.topic.VTopicDTO;
import com.blackphoenixproductions.forumfrontend.dto.user.UserDTO;
import com.blackphoenixproductions.forumfrontend.enums.Pagination;
import com.blackphoenixproductions.forumfrontend.security.KeycloakUtility;
import com.blackphoenixproductions.forumfrontend.utility.FilterUtility;
import com.blackphoenixproductions.forumfrontend.utility.ValueUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Controller
public class PageController {

    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    private final ValueUtility valueUtility;
    private final ForumClient forumClient;

    @Autowired
    public PageController(ValueUtility valueUtility, ForumClient forumClient) {
        this.valueUtility = valueUtility;
        this.forumClient = forumClient;
    }

    @GetMapping (value = "/search")
    public String searchPage (Model model,
                              HttpServletRequest req, Principal principal,
                              @RequestParam(required = false) String authorUsername,
                              @RequestParam(required = false) Long page,
                              @RequestParam(required = false) String title)
                              throws Exception {
        PagedModel<EntityModel<VTopicDTO>> pagedTopics = null;

        Map<String, String> paramsMap = new HashMap();
        paramsMap.put("title", title);
        paramsMap.put("authorUsername", authorUsername);
        Filter filter = FilterUtility.buildFilter(paramsMap);

        if(page != null){
            pagedTopics = forumClient.findFilteredTopicsByPage(page.intValue(), Pagination.TOPIC_PAGINATION.getValue(), filter).getBody();
        } else{
            pagedTopics = forumClient.findFilteredTopicsByPage(0, Pagination.TOPIC_PAGINATION.getValue(), filter).getBody();
        }
        setCommonAttributes(model, principal);
        model.addAttribute("title", title);
        model.addAttribute("pagedTopics", pagedTopics);
        return "forum-search";
    }


    @GetMapping(value = "/profile")
    public String profilePage(Model model,
                              Principal principal,
                              HttpServletRequest req) throws Exception {
        model.addAttribute("roles", KeycloakUtility.getRoles(req));
        setCommonAttributes(model, principal);
        return "forum-profile";
    }

    @GetMapping (value = {"/forum", "/"})
    public String forumPage(Model model,
                            HttpServletRequest req,
                            Principal principal,
                            @RequestParam(required = false) Long page) throws Exception {
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
    public String viewTopic(Model model,
                            HttpServletRequest req,
                            @RequestParam Long id,
                            @RequestParam(required = false) Long page,
                            Principal principal) throws Exception {
        PagedModel<EntityModel<PostDTO>> postPageDTO = null;
        TopicDTO topicDTO = forumClient.findTopic(id).getBody().getContent();
        if(page != null) {
            postPageDTO = forumClient.findPostsByPage(id, page.intValue(), Pagination.POST_PAGINATION.getValue()).getBody();
        }
        else {
            postPageDTO = forumClient.findPostsByPage(id, 0, Pagination.POST_PAGINATION.getValue()).getBody();
        }
        model.addAttribute("topic", topicDTO);
        model.addAttribute("pagedPosts", postPageDTO);
        setCommonAttributes(model, principal);
        return "forum-single";
    }

    @GetMapping (value="/notification")
    public String notification(Model model,
                               HttpServletRequest httpServletRequest,
                               Principal principal) {
        CollectionModel<NotificationDTO> notificationDTOList = null;
        if(principal != null){
            notificationDTOList = forumClient.getUserNotificationList(KeycloakUtility.getBearerTokenString(principal)).getBody();
        }
        model.addAttribute("notificationList", notificationDTOList);
        model.addAttribute("domain", valueUtility.getDomain());
        return "notification :: notification_fragment";
    }

    @GetMapping (value="/readedNotification")
    public @ResponseBody
    void readedNotification (HttpServletRequest httpServletRequest,
                             Principal principal){
        if(principal != null) {
            forumClient.setNotificationStatus(KeycloakUtility.getBearerTokenString(principal), false);
        }
    }


    private void setCommonAttributes (Model model, Principal principal) throws Exception {
        UserDTO userDTO = null;
        Collection<NotificationDTO> notificationDTOList = null;
        Boolean userNotificationStatus = null;
        if(principal != null) {
            userDTO = forumClient.retriveUser(KeycloakUtility.getBearerTokenString(principal)).getBody().getContent();
            CollectionModel<NotificationDTO> notifications = forumClient.getUserNotificationList(KeycloakUtility.getBearerTokenString(principal)).getBody();
            if(notifications != null){
                notificationDTOList =  forumClient.getUserNotificationList(KeycloakUtility.getBearerTokenString(principal)).getBody().getContent();
            }
            userNotificationStatus = forumClient.getUserNotificationStatus(KeycloakUtility.getBearerTokenString(principal)).getBody();
            model.addAttribute("token", KeycloakUtility.getAccessTokenString(principal));
        }
        String buildVersionBE = forumClient.getBuildVersionBackEnd().getBody();
        model.addAttribute("user", userDTO);
        model.addAttribute("domain", valueUtility.getDomain());
        model.addAttribute("sseBackend", valueUtility.getSseBackend());
        model.addAttribute("notificationList", notificationDTOList);
        model.addAttribute("userNotificationStatus", userNotificationStatus);
        model.addAttribute("buildVersionBE", buildVersionBE);
        model.addAttribute("buildVersionFE", valueUtility.getBuildVersion());
    }


}
