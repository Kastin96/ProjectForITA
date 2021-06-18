package com.example.controllers.lifecycle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(path = "/logout")
public class LogoutController {

    @GetMapping
    protected ModelAndView logOut(HttpSession session) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.clear();
        session.invalidate();

        modelAndView.setViewName("authentication");
        return modelAndView;
    }

}
