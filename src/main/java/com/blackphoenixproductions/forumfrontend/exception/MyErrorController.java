package com.blackphoenixproductions.forumfrontend.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(MyErrorController.class);


    @GetMapping(value = "/error")
    public String genericError(Model model, HttpServletRequest httpServletRequest) {
        Object status = httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        logger.error("Errore - statusCode : {}", status);
        model.addAttribute("status", status);
        return "error";
    }

}
