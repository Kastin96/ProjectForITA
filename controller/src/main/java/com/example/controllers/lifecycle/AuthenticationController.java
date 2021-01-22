package com.example.controllers.lifecycle;

import com.example.controllerservice.AuthenticationService;
import com.example.controllerservice.InitAdmin;
import com.example.localdatabase.UserDatabase;
import com.example.hardcoremetod.HardcoreMethod;
import com.example.users.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "UserServlet", urlPatterns = "/authentication", initParams = {
        @WebInitParam(name = "adminLogin", value = "admin"),
        @WebInitParam(name = "adminPass", value = "admin")})
public class AuthenticationController extends HttpServlet {
    Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Override
    public void init(ServletConfig config){
        String adminLogin = config.getInitParameter("adminLogin");
        String adminPass = config.getInitParameter("adminPass");

        InitAdmin.init(adminLogin, adminPass);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();

        final Optional<? extends User> userByLogin = AuthenticationService.getUserByLogin(login, password);

        if (userByLogin.isPresent()) {
            final User user = userByLogin.get();

            if (user.getLogin().equalsIgnoreCase(login)) {
                if (user.getPassword().equals(password)) {
                    session.setAttribute("user", user);
                    session.setAttribute("userClass", user.getClass());
                    session.setAttribute("id", user.getId());
                    session.setAttribute("login", user.getLogin());

                    if (user instanceof Administrator) {
                        session.setAttribute("isAdmin", true);
                        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/adminpage");
                        requestDispatcher.forward(req, resp);

                        log.info("Admin logged in = {}", user.getLogin());
                    } else if (user instanceof Trainer) {
                        session.setAttribute("isTrainer", true);
                        session.setAttribute("salaryList", ((Trainer) user).getSalaryList());
                        session.setAttribute("name", ((Trainer) user).getFullName());
                        session.setAttribute("age", ((Trainer) user).getAge());
                        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/trainerpage");
                        requestDispatcher.forward(req, resp);

                        log.info("Trainer logged in = {}", user.getLogin());
                    } else if (user instanceof Student){
                        session.setAttribute("name", ((Student) user).getFullName());
                        session.setAttribute("age", ((Student) user).getAge());
                        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/userpage");
                        requestDispatcher.forward(req, resp);

                        log.info("Student logged in = {}", user.getLogin());
                    }
                }
            }
            session.setAttribute("badAuthentication", "You entered incorrect login information!");
        }
        resp.sendRedirect("/new");
    }
}
