package com.example.controllers.salary;

import com.example.database.UserDatabase;
import com.example.salary.AverageSalary;
import com.example.users.Trainer;
import com.example.users.User;

import javax.lang.model.element.ModuleElement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@WebServlet(urlPatterns = "/countaveragesalary")
public class AverageSalaryController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        if (user instanceof Trainer){
            BigDecimal averageSalary = AverageSalary.getCount(((Trainer) user).getSalaryList());
            session.setAttribute("averageSalary", averageSalary);
        } else {
            session.setAttribute("averageSalary", "Average salary is not available!");
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/salarylist");
        requestDispatcher.forward(req, resp);
    }
}
