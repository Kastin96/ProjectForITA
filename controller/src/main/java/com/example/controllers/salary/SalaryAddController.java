package com.example.controllers.salary;

import com.example.controllers.lifecycle.AuthenticationController;
import com.example.users.Trainer;
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

@WebServlet(urlPatterns = "/addsalary")
public class SalaryAddController extends HttpServlet {
    Logger log = LoggerFactory.getLogger(SalaryAddController.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int addSalary = Integer.parseInt(req.getParameter("addSalary"));

        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");

        if (user instanceof Trainer){
            ((Trainer) user).addSalary(addSalary);
            session.setAttribute("salaryAdded", "Salary added to your list!");

            log.info("Trainer added salary ={}", session.getAttribute("login"));
        } else {
            log.warn("Error added salary ={}", session.getAttribute("login"));

            session.setAttribute("salaryAdded", "Error: Salary has not been added!");
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/salarylist");
        requestDispatcher.forward(req, resp);
    }
}
