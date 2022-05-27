package com.blackphoenixproductions.forumfrontend.controller;


import com.blackphoenixproductions.forumfrontend.utility.CookieUtility;
import com.blackphoenixproductions.forumfrontend.utility.ValueUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class UserController {

    private final ValueUtility valueUtility;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    public UserController(ValueUtility valueUtility) {
        this.valueUtility = valueUtility;
    }


    @GetMapping (value = "/logout")
    public String logout(HttpServletResponse httpServletResponse){
        logger.info("Start logout");
        // todo chiamare api keycloak
        logger.info("Start logout");
        return "redirect:/forum";
    }

    @GetMapping (value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse httpServletResponse){
        logger.info("Start login");
        logger.info("End login");
        return "redirect:/forum";
    }



}
