package com.example.controllers.lifecycle;

import com.example.users.Administrator;
import com.example.users.Student;
import com.example.users.Trainer;
import com.example.users.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


//@WebServlet(urlPatterns = "/", initParams = {
//        @WebInitParam(name = "adminLogin", value = "admin"),
//        @WebInitParam(name = "adminPass", value = "admin")})
@Controller
@RequestMapping(path = "/")
public class SessionController {

//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        String adminLogin = config.getInitParameter("adminLogin");
//        String adminPass = config.getInitParameter("adminPass");
//
//        InitAdmin.init(adminLogin, adminPass);
//
//        HardcoreMethod.run(20, 5);
//        super.init(config);
//    }

    @GetMapping()
    protected ModelAndView checkAuthorization(HttpSession session) {
        final User user = (User) session.getAttribute("user");
        final ModelAndView modelAndView = new ModelAndView();

        if (session.isNew()) {
            modelAndView.setViewName("authentication");
        } else {
            if (user instanceof Administrator) {
                modelAndView.setViewName("adminpage");
            } else if (user instanceof Trainer) {
                modelAndView.setViewName("trainerpage");
            } else if (user instanceof Student) {
                modelAndView.setViewName("userpage");
            } else {
                modelAndView.setViewName("authentication");
            }
        }
        return modelAndView;
    }
}
