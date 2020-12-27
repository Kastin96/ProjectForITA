package com.example.controllers.lifecycle;

import com.example.database.UserDatabase;
import com.example.search.SearchFromDatabase;
import com.example.users.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");

        try {
            int age = Integer.parseInt(req.getParameter("age"));

            if (SearchFromDatabase.findUserFromUserDatabase(login) == null) {
                Student student = new Student(login, password, name, age);
                UserDatabase.getInstance().put(student.getId(), student);

                session.setAttribute("goodRegistration", "Registration was successful!");

                log.info("New Student added = {}", student);

                resp.sendRedirect("/new/signin");
            } else {
                session.setAttribute("badRegistration", "This login is already in use!");

                resp.sendRedirect("/new/reg");
            }
        } catch (NumberFormatException e) {
            session.setAttribute("badRegistration", "Age error: Number must be entered!");

            resp.sendRedirect("/new/reg");
        }
    }
}
