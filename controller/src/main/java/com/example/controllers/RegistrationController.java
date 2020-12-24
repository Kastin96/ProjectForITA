package com.example.controllers;

import com.example.database.UserDataBase;
import com.example.users.Student;
import com.example.users.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;


@WebServlet(urlPatterns = "/registration")
public class RegistrationController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));

        Student student = new Student(login, password, name, age);
        UserDataBase.getInstance().put(student.getId(), student);

        HttpSession session = req.getSession();

        Collection<Object> values = UserDataBase.getInstance().values();


        session.setAttribute("out", values);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/reg-result");
        requestDispatcher.forward(req, resp);
    }

}
