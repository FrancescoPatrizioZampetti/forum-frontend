package com.blackphoenixproductions.forumfrontend.controller;

import com.blackphoenixproductions.forumfrontend.client.ForumClient;
import com.blackphoenixproductions.forumfrontend.dto.post.PostDTO;
import com.blackphoenixproductions.forumfrontend.utility.CookieUtility;
import com.blackphoenixproductions.forumfrontend.utility.ValueUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


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
    public String searchPage (Model model, HttpServletRequest httpServletRequest, @RequestParam(required = false) Long page, @RequestParam(required = false) String title,  @RequestParam(required = false) String username) throws Exception {
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
    public String profilePage(Model model, HttpServletRequest httpServletRequest) throws Exception {
        String jwtToken = CookieUtility.getTokenFromCookie(httpServletRequest, CookieUtility.ACCESS_TOKEN_NAME);
        setCommonAttributes(model, jwtToken);
        return "forum-profile";
    }

    @GetMapping (value = {"/forum", "/"})
    public String forumPage(Model model, HttpServletRequest httpServletRequest, @RequestParam(required = false) Long page) throws Exception {
//        PagedModel<EntityModel<SimpleTopicDTO>> pagedTopics = null;
        String jwtToken = null; // todo recuperare da request
        Integer totalUsers = (Integer) forumClient.getTotalUsers().getBody().getContent().getValue();
//        Long totalTopics = backendCaller.getTotalTopics();
//        Long totalPosts = backendCaller.getTotalPosts();
        if(page == null) {
//            pagedTopics = backendCaller.getTopicsByPage(0L, Pagination.TOPIC_PAGINATION.getValue(), null, null);
        }
        else {
//            pagedTopics = backendCaller.getTopicsByPage(page, Pagination.TOPIC_PAGINATION.getValue(), null, null);
        }
        setCommonAttributes(model, jwtToken);
//        model.addAttribute("totalusers", totalUsers);
//        model.addAttribute("totaltopics", totalTopics);
//        model.addAttribute("totalposts", totalPosts);
//        model.addAttribute("pagedTopics", pagedTopics);
        return "forum";
    }


    @GetMapping (value = "/initresetcredentials")
    public String resetCredentialsPage (Model model, HttpServletRequest httpServletRequest) throws Exception {
        String jwtToken = CookieUtility.getTokenFromCookie(httpServletRequest, CookieUtility.ACCESS_TOKEN_NAME);
        setCommonAttributes(model, jwtToken);
        return "forum-init-reset";
    }

    @GetMapping (value = "/finishresetcredentials")
    public String finishResetCredentialsPage (@RequestParam String token, @RequestParam String username, Model model, HttpServletRequest httpServletRequest) throws Exception {
        String jwtToken = CookieUtility.getTokenFromCookie(httpServletRequest, CookieUtility.ACCESS_TOKEN_NAME);
        model.addAttribute("reset_token", token);
        model.addAttribute("username", username);
        setCommonAttributes(model, jwtToken);
        return "forum-finish-reset";
    }


    @GetMapping (value="/viewtopic")
    public String viewTopic(Model model, HttpServletRequest httpServletRequest, @RequestParam Long id, @RequestParam(required = false) Long page) throws Exception {
        String jwtToken = CookieUtility.getTokenFromCookie(httpServletRequest, CookieUtility.ACCESS_TOKEN_NAME);
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
        setCommonAttributes(model, jwtToken);
        return "forum-single";
    }

    @GetMapping (value="/notification")
    public String notification(Model model, HttpServletRequest httpServletRequest) {
        String jwtToken = CookieUtility.getTokenFromCookie(httpServletRequest, CookieUtility.ACCESS_TOKEN_NAME);
//        List<NotificationDTO> notificationDTOList = backendCaller.getUserNotificationList(jwtToken);
//        model.addAttribute("notificationList", notificationDTOList);
        model.addAttribute("domain", valueUtility.getDomain());
        return "notification :: notification_fragment";
    }

    @GetMapping (value="/readedNotification")
    public @ResponseBody
    void readedNotification (HttpServletRequest httpServletRequest){
        String jwtToken = CookieUtility.getTokenFromCookie(httpServletRequest, CookieUtility.ACCESS_TOKEN_NAME);
//        backendCaller.setReadedNotificationStatus(jwtToken);
    }



    private void setCommonAttributes (Model model, String jwtToken) throws Exception {
//        SimpleUserDTO simpleUserDTO = backendCaller.getLoggedUser(jwtToken);
//        List<NotificationDTO> notificationDTOList = backendCaller.getUserNotificationList(jwtToken);
//        String buildVersionBE = backendCaller.getBuildVersionBackEnd();
//        Boolean userNotificationStatus = backendCaller.getUserNotificationStatus(jwtToken);
//        model.addAttribute("user", simpleUserDTO);
//        model.addAttribute("token", jwtToken);
//        model.addAttribute("domain", valueUtility.getDomain());
//        model.addAttribute("sseBackend", valueUtility.getSseBackend());
//        model.addAttribute("notificationList", notificationDTOList);
//        model.addAttribute("userNotificationStatus", userNotificationStatus);
//        model.addAttribute("buildVersionBE", buildVersionBE);
        model.addAttribute("buildVersionFE", valueUtility.getBuildVersion());
    }


}
