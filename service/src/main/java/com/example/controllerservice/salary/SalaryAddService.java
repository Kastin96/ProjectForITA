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
public class SalaryAddService {
    @Autowired
    private TrainerRepositoryHibernate trainerRepository;

    public boolean add(User user, Integer salary) {
        return TrainerRepositoryPostgres.getInstance().addSalary(user.getId(), salary);
    }

    public boolean addByHibernate(User user, Integer salary) {
        final Optional<Trainer> foundedTrainer = trainerRepository.find(user.getId());
        if (foundedTrainer.isPresent()) {
            final Trainer trainer = foundedTrainer.get();
            final List<Integer> salaryList = trainer.getSalaryList();
            salaryList.add(salary);
            trainer.setSalaryList(salaryList);
            return trainerRepository.save(trainer);
        }
        return false;
    }
}
