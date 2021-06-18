package com.example.controllers.lifecycle;

import com.example.controllerservice.lifecycle.AuthenticationService;
import com.example.controllerservice.salary.AverageSalaryCounter;
import com.example.users.Administrator;
import com.example.users.Student;
import com.example.users.Trainer;
import com.example.users.User;
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
import java.math.BigDecimal;
import java.util.Optional;

@WebServlet(name = "UserServlet", urlPatterns = "/authentication")
public class AuthenticationController extends HttpServlet {
    Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();

//        final Optional<? extends User> userByLogin = AuthenticationService.getUserByLogin(login, password);
        final Optional<? extends User> userByLogin = AuthenticationService.getUserByLoginFromHibernate(login, password);

        if (userByLogin.isPresent()) {
            final User user = userByLogin.get();

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
//                BigDecimal averageSalary = AverageSalaryCounter.count(user);
                BigDecimal averageSalary = AverageSalaryCounter.countByHibernate(user);
                if (averageSalary != null) {
                    session.setAttribute("averageSalary", averageSalary);
                }
                session.setAttribute("name", ((Trainer) user).getFullName());
                session.setAttribute("age", ((Trainer) user).getAge());
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/trainerpage");
                requestDispatcher.forward(req, resp);

                log.info("Trainer logged in = {}", user.getLogin());
            } else if (user instanceof Student) {
                session.setAttribute("name", ((Student) user).getFullName());
                session.setAttribute("age", ((Student) user).getAge());
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/userpage");
                requestDispatcher.forward(req, resp);

                log.info("Student logged in = {}", user.getLogin());
            }
        } else {
            req.setAttribute("badAuthentication", "You entered incorrect login information!");
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/signin");
        requestDispatcher.forward(req, resp);
    }
}
