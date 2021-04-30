package com.example.controllers.lifecycle;

import com.example.controllerservice.lifecycle.AuthenticationService;
import com.example.controllerservice.salary.AverageSalaryCounter;
import com.example.users.Administrator;
import com.example.users.Student;
import com.example.users.Trainer;
import com.example.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping(path = "/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;
    @Autowired
    private AverageSalaryCounter averageSalaryCounter;

    @GetMapping()
    public ModelAndView showAuthenticationPage() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("authentication");
        return modelAndView;
    }

    @PostMapping()
    public ModelAndView checkAuthentication(@RequestParam(name = "login") String login,
                                            @RequestParam(name = "password") String password, HttpSession session) {
        final ModelAndView modelAndView = new ModelAndView();

        final Optional<? extends User> userByLogin = authService.getUserByLoginFromHibernate(login, password);

        if (userByLogin.isPresent()) {
            final User user = userByLogin.get();

            if (user instanceof Administrator) {
                final Administrator administrator = (Administrator) user;

                session.setAttribute("user", administrator);
                session.setAttribute("isAdmin", true);

                modelAndView.setViewName("adminpage");
            } else if (user instanceof Trainer) {
                final Trainer trainer = (Trainer) user;

                session.setAttribute("user", trainer);
                session.setAttribute("isTrainer", true);

                BigDecimal averageSalary = averageSalaryCounter.countByHibernate(trainer);
                if (averageSalary != null) {
                    modelAndView.addObject("averageSalary", averageSalary);
                }

                modelAndView.setViewName("trainerpage");
            } else if (user instanceof Student) {
                final Student student = (Student) user;

                session.setAttribute("user", student);

                modelAndView.setViewName("userpage");
            }
        } else {
            modelAndView.addObject("badAuthentication", "You entered incorrect login information!");
            modelAndView.setViewName("authentication");
        }

        return modelAndView;
    }
}
