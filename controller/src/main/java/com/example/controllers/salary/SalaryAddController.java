package com.example.controllers.salary;

import com.example.salary.AverageSalary;
import com.example.users.Trainer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = "/addsalary")
public class SalaryAddController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer addSalary = Integer.parseInt(req.getParameter("addSalary"));

        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");

        if (user instanceof Trainer){
            ((Trainer) user).addSalary(addSalary);
            session.setAttribute("salaryAdded", "Salary added to your list!");
        } else {
            session.setAttribute("salaryAdded", "Error: Salary has not been added!");
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/salarylist");
        requestDispatcher.forward(req, resp);
    }
}
