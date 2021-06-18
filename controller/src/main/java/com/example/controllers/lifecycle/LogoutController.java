package com.example.controllers.lifecycle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
public class LogoutController {

    @GetMapping("/logout")
    protected ModelAndView logOut(HttpSession session) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.clear();
        session.invalidate();

        modelAndView.setViewName("authentication");
        return modelAndView;
    }

}
