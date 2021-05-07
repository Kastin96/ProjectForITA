package com.example.controllers.lifecycle;

import com.example.controllerservice.lifecycle.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(path = "/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @GetMapping
    public ModelAndView showRegistrationPage() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping()
    public ModelAndView registration(@RequestParam(name = "login") String login,
                                     @RequestParam(name = "password") String password,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "age") Integer age) {

        final boolean isGoodReg = registrationService.registrationStudent(login, password, name, age);
        final ModelAndView modelAndView = new ModelAndView();

        if (isGoodReg) {
            modelAndView.addObject("goodRegistration", "Registration was successful!");
            modelAndView.setViewName("authentication");
        } else {
            modelAndView.addObject("badRegistration", "This login is already in use!");
            modelAndView.setViewName("registration");
        }

        return modelAndView;
    }
}
