package com.blackphoenixproductions.forumfrontend.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {


    @GetMapping(value="/error")
    public String genericError(HttpServletRequest httpServletRequest){
        return "redirect:/login";
    }

}
