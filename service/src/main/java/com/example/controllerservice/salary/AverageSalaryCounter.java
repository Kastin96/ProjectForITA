package com.example.controllerservice.salary;

import com.example.database.usersrepository.TrainerRepositoryHibernate;
import com.example.database.usersrepository.TrainerRepositoryPostgres;
import com.example.salary.AverageSalary;
import com.example.users.Trainer;
import com.example.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AverageSalaryCounter {
    @Autowired
    private TrainerRepositoryHibernate trainerRepository;
    @Autowired
    private AverageSalary averageSalary;

    public BigDecimal count(User user) {
        return TrainerRepositoryPostgres.getInstance().getAverageSalary(user.getId());
    }

    public BigDecimal countByHibernate(User user) {
        final Optional<Trainer> trainer = trainerRepository.find(user.getId());
        if (trainer.isPresent()) {
            final List<Integer> salaryList = trainer.get().getSalaryList();
            if (!salaryList.isEmpty()) {
                return averageSalary.count(salaryList);
            }
        }
        return count(user);
    }
}
