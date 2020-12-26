package com.example.controllers.lifecycle;

import com.example.database.UserDatabase;
import com.example.hardcoremetod.HardcoreMethod;
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
import java.util.Collection;
import java.util.Date;


@WebServlet(urlPatterns = "/registration")
public class RegistrationController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");

        try {
            int age = Integer.parseInt(req.getParameter("age"));

            Student student = new Student(login, password, name, age);
            UserDatabase.getInstance().put(student.getId(), student);

            session.setAttribute("goodRegistration", "Registration was successful!");

            resp.sendRedirect("/new/signin");
        } catch (NumberFormatException e) {
            session.setAttribute("badRegistration", "Age error: Number must be entered!");

            resp.sendRedirect("/new/reg");
        }
    }
}
