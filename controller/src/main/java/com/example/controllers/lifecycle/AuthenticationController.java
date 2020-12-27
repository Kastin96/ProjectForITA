package com.example.controllers.lifecycle;

import com.example.database.UserDatabase;
import com.example.hardcoremetod.HardcoreMethod;
import com.example.users.Administrator;
import com.example.users.Trainer;
import com.example.users.User;

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

    @Override
    public void init(ServletConfig config) throws ServletException {
        HardcoreMethod.run(20, 5);

        String adminLogin = config.getInitParameter("adminLogin");
        String adminPass = config.getInitParameter("adminPass");

        Administrator administrator = new Administrator(adminLogin,
                adminPass,
                "adminAdmin",
                24);

        UserDatabase.getInstance().put(administrator.getId(), administrator);
    }

     @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();

        for (Map.Entry<UUID, User> userEntry : UserDatabase.getInstance().entrySet()) {
            User user = userEntry.getValue();
            if (user.getLogin().equalsIgnoreCase(login)) {
                if (user.getPassword().equals(password)) {
                    session.setAttribute("user", user);
                    session.setAttribute("userClass", user.getClass());
                    session.setAttribute("id", user.getId());
                    session.setAttribute("login", user.getLogin());
                    session.setAttribute("name", user.getFullName());
                    session.setAttribute("age", user.getAge());

                    if (user instanceof Administrator) {
                        session.setAttribute("isAdmin", true);
                        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/adminpage");
                        requestDispatcher.forward(req, resp);
                    } else if (user instanceof Trainer) {
                        session.setAttribute("isTrainer", true);
                        session.setAttribute("salaryList", ((Trainer) user).getSalaryList());
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
