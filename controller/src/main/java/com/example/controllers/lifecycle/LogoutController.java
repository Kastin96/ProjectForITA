package com.example.controllers.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Slf4j
@Controller
@RequestMapping(path = "/logout")
public class LogoutController {

    @GetMapping
    protected ModelAndView logOut(HttpSession session) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.clear();
        session.invalidate();
        log.info("The session has been canceled!");

        modelAndView.setViewName("authentication");
        return modelAndView;
    }

}
