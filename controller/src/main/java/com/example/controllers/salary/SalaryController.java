package com.example.controllers.salary;

import com.example.controllerservice.salary.AverageSalaryCounter;
import com.example.controllerservice.salary.SalaryAddService;
import com.example.controllerservice.salary.SalaryShowService;
import com.example.users.Trainer;
import com.example.users.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping(path = "/salarylist")
@AllArgsConstructor
public class SalaryController {

    private AverageSalaryCounter averageSalaryCounter;
    private SalaryAddService salaryAddService;
    private SalaryShowService salaryShowService;

    @GetMapping
    public ModelAndView showAverageSalary(HttpSession session) {
        User user = (User) session.getAttribute("user");
        final ModelAndView modelAndView = new ModelAndView();

        if (user instanceof Trainer) {
            final List<Integer> salaryList = ((Trainer) user).getSalaryList();
            if (!salaryList.isEmpty()) {
                modelAndView.addObject("salaryList", salaryShowService.getSalaryList(user));
            }

            BigDecimal averageSalary = averageSalaryCounter.count(user);
            if (averageSalary != null) {
                modelAndView.addObject("averageSalary", averageSalary);
            }
        }
        modelAndView.setViewName("salarylistpage");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView addSalaryToTrainer(@RequestParam(name = "addSalary") Integer addSalary, HttpSession session) {
        User user = (User) session.getAttribute("user");
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("salarylistpage");

        if (user instanceof Trainer) {
            final boolean isAdd = salaryAddService.add(user, addSalary);

            if (isAdd) {
                modelAndView.addObject("salaryAdded", "Salary added to your list!");
                modelAndView.addObject("salaryList", salaryShowService.getSalaryList(user));

                BigDecimal averageSalary = averageSalaryCounter.count(user);
                if (averageSalary != null) {
                    modelAndView.addObject("averageSalary", averageSalary);
                }
            }
        } else {
            modelAndView.addObject("salaryAdded", "Error: Salary has not been added!");
        }
        return modelAndView;
    }
}
