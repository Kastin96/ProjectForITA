package com.example.controllers.lifecycle;

import com.example.database.UserDatabase;
import com.example.hardcoremetod.HardcoreMethod;
import com.example.users.Administrator;
import com.example.users.Student;
import com.example.users.Trainer;
import com.example.users.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;


@WebServlet(urlPatterns = "/authentication")
public class AuthenticationController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HardcoreMethod.run();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();

        for (Map.Entry<UUID, User> next : UserDatabase.getInstance().entrySet()) {
            User value = next.getValue();
            if (value.getLogin().equalsIgnoreCase(login)) {
                if (value.getPassword().equals(password)) {
                    session.setAttribute("login", value.getLogin());
                    session.setAttribute("name", value.getFullName());
                    session.setAttribute("age", value.getAge());
                    session.setAttribute("userClass", value.getClass());
                    session.setAttribute("user", value);
                    session.setAttribute("id", value.getId());

                    if (value instanceof Administrator) {
                        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/adminpage");
                        requestDispatcher.forward(req, resp);
                    } else if (value instanceof Trainer){
                        session.setAttribute("salaryList", ((Trainer) value).getSalaryList());
                        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/trainerpage");
                        requestDispatcher.forward(req, resp);
                    } else {
                        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/userpage");
                        requestDispatcher.forward(req, resp);
                    }
                }
            }
            session.setAttribute("badAuthentication", "You entered incorrect login information!");
        }

        resp.sendRedirect("/new");
    }
}
