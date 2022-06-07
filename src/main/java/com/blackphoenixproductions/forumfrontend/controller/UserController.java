package com.blackphoenixproductions.forumfrontend.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;


@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @GetMapping (value = "/logout")
    public String logout(HttpServletRequest req){
        logger.info("Start logout");
        // todo chiamare api keycloak
        logger.info("End logout");
        return "redirect:/forum";
    }

    @GetMapping (value = "/login")
    public String login(HttpServletRequest req, Principal principal){
        logger.info("Start login");
        logger.info("End login");
        return "redirect:/forum";
    }



}
