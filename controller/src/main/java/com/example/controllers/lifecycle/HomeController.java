package com.example.controllers.lifecycle;

import com.example.users.Administrator;
import com.example.users.Student;
import com.example.users.Trainer;
import com.example.users.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/home")
public class HomeController {

    @GetMapping
    public ModelAndView viewHomePageByRole(HttpSession session){
        final ModelAndView modelAndView = new ModelAndView();

        User user = (User) session.getAttribute("user");

        if (user instanceof Administrator){
            modelAndView.setViewName("adminpage");
        } else if (user instanceof Trainer){
            modelAndView.setViewName("trainerpage");
        } else if (user instanceof Student){
            modelAndView.setViewName("userpage");
        } else {
            modelAndView.setViewName("authentication");
        }

        return modelAndView;
    }
}
