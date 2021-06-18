package com.example.controllerservice.salary;

import com.example.database.hibernate.TrainerRepositoryHibernate;
import com.example.database.postgres.TrainerRepositoryPostgres;
import com.example.repositoryaccess.TrainerService;
import com.example.users.Trainer;
import com.example.users.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class SalaryAddService {
    private TrainerService trainerService;

    public boolean add(User user, Integer salary) {
        return TrainerRepositoryPostgres.getInstance().addSalary(user.getId(), salary);
    }

    public boolean addByHibernate(User user, Integer salary) {
        final Optional<Trainer> foundedTrainer = trainerService.find(user.getId());
        if (foundedTrainer.isPresent()) {
            Trainer trainer = foundedTrainer.get();

            List<Integer> salaryList = trainer.getSalaryList();
            salaryList.add(salary);
            trainer.setSalaryList(salaryList);

            return trainerService.save(trainer);
        }
        return false;
    }
}
