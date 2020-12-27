package com.example.controllers.lifecycle;

import com.example.database.UserDatabase;
import com.example.hardcoremetod.HardcoreMethod;
import com.example.users.Administrator;
import com.example.users.Student;
import com.example.users.Trainer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = "/")
public class SessionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object userClass = session.getAttribute("userClass");

        if (session.isNew()) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/signin");
            requestDispatcher.forward(req, resp);
        } else  {
            if (userClass == Administrator.class) {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/adminpage");
                requestDispatcher.forward(req, resp);
            } else if (userClass == Trainer.class){
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/trainerpage");
                requestDispatcher.forward(req, resp);
            } else if (userClass == Student.class){
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/userpage");
                requestDispatcher.forward(req, resp);
            } else {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/signin");
                requestDispatcher.forward(req, resp);
            }
        }
    }
}