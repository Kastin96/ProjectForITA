package com.example.controllerservice.salary;

import com.example.repositoryaccess.TrainerService;
import com.example.salary.AverageSalary;
import com.example.users.Trainer;
import com.example.users.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AverageSalaryCounter {
    private TrainerService trainerService;
    private AverageSalary averageSalary;

    public BigDecimal count(User user) {
        final Optional<Trainer> trainer = trainerService.find(user.getId());
        if (trainer.isPresent()) {
            final List<Integer> salaryList = trainer.get().getSalaryList();
            if (!salaryList.isEmpty()) {
                return averageSalary.count(salaryList);
            }
        }
        return null;
    }
}
