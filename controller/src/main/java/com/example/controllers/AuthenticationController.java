package com.example.controllers;

import com.example.database.UserDataBase;
import com.example.users.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


@WebServlet(urlPatterns = "/authentication")
public class AuthenticationController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();

        for (Map.Entry<UUID, Object> next : UserDataBase.getInstance().entrySet()) {
            Student value = (Student) next.getValue();
            if (value.getLogin().equalsIgnoreCase(login)) {
                if (value.getPassword().equals(password)) {
                    session.setAttribute("authentication", value);
                }
            } else {
                session.setAttribute("authentication", "Всё очень плохо!");
            }
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/aut-result");
        requestDispatcher.forward(req, resp);
    }

}
