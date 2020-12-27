package com.example.controllers.lifecycle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = "/logout")
public class LogoutController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        session.removeAttribute("userClass");
        session.removeAttribute("id");
        session.removeAttribute("login");
        session.removeAttribute("name");
        session.removeAttribute("age");
        session.removeAttribute("isAdmin");
        session.removeAttribute("isTrainer");
        session.removeAttribute("salaryList");

        session.invalidate();

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/");
        requestDispatcher.forward(req,resp);
    }

}
