package com.blackphoenixproductions.forumfrontend.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @GetMapping (value = "/logout")
    public String logout(HttpServletRequest req) throws ServletException {
        logger.info("Start logout");
        req.logout();
        logger.info("End logout");
        return "redirect:/forum";
    }

    @GetMapping (value = "/login")
    public String login(HttpServletRequest req,
                        Principal principal){
        logger.info("Start login");
        logger.info("Logged in : {}", principal.getName());
        logger.info("End login");
        return "redirect:/forum";
    }



}
