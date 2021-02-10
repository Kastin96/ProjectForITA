package com.example.controllers.salary;

import com.example.controllerservice.salary.AverageSalaryCounter;
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
import java.math.BigDecimal;

@WebServlet(urlPatterns = "/countaveragesalary")
public class AverageSalaryController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        clearSessionAttribute(session);

        User user = (User) session.getAttribute("user");

        if (user instanceof Trainer) {
            BigDecimal averageSalary = AverageSalaryCounter.count(user);
            if (averageSalary != null) {
                session.setAttribute("averageSalary", averageSalary);
            }
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/salarylist");
        requestDispatcher.forward(req, resp);
    }

    private void clearSessionAttribute(HttpSession session) {
        session.removeAttribute("averageSalary");
    }
}
