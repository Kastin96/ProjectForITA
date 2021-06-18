package com.example.controllerservice.salary;

import com.example.database.hibernate.TrainerRepositoryHibernate;
import com.example.database.postgres.TrainerRepositoryPostgres;
import com.example.repositoryaccess.TrainerService;
import com.example.users.Trainer;
import com.example.users.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SalaryShowService {
    private TrainerService trainerService;

    public List<Integer> getSalaryList(User user) {
        return TrainerRepositoryPostgres.getInstance().getSalaryList(user.getId());
    }

    public List<Integer> getSalaryListByHibernate(User user) {
        final Optional<Trainer> trainer = trainerService.find(user.getId());
        return trainer.map(Trainer::getSalaryList).orElse(getSalaryList(user));
    }
}
