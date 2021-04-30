package com.example.controllerservice.salary;

import com.example.database.usersrepository.TrainerRepositoryHibernate;
import com.example.database.usersrepository.TrainerRepositoryPostgres;
import com.example.users.Trainer;
import com.example.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryShowService {
    @Autowired
    private TrainerRepositoryHibernate trainerRepository;

    public List<Integer> getSalaryList(User user) {
        return TrainerRepositoryPostgres.getInstance().getSalaryList(user.getId());
    }

    public List<Integer> getSalaryListByHibernate(User user) {
        final Optional<Trainer> trainer = trainerRepository.find(user.getId());
        return trainer.map(Trainer::getSalaryList).orElse(getSalaryList(user));
    }
}
