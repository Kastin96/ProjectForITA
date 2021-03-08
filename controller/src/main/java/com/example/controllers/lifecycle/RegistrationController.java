package com.example.controllers.lifecycle;

import com.example.controllerservice.lifecycle.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = "/registration")
public class RegistrationController extends HttpServlet {
    Logger log = LoggerFactory.getLogger(RegistrationController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        Integer age = Integer.parseInt(req.getParameter("age"));

//        final boolean isGoodReg = RegistrationService.registrationStudentByPostgres(login, password, name, age);
        final boolean isGoodReg = RegistrationService.registrationStudentByHibernate(login, password, name, age);

        if (isGoodReg) {
            req.setAttribute("goodRegistration", "Registration was successful!");
            log.info("New Student added = {}", login);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/signin");
            requestDispatcher.forward(req, resp);
        } else {
            req.setAttribute("badRegistration", "This login is already in use!");

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/reg");
            requestDispatcher.forward(req, resp);
        }
    }
}
