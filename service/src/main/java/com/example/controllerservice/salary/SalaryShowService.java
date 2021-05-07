package com.example.controllerservice.salary;

import com.example.repositoryaccess.TrainerService;
import com.example.users.Trainer;
import com.example.users.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SalaryShowService {
    private TrainerService trainerService;

    public List<Integer> getSalaryListByHibernate(User user) {
        List<Integer> result = new ArrayList<>();
        final Optional<Trainer> trainer = trainerService.find(user.getId());
        if (trainer.isPresent()){
            result = trainer.get().getSalaryList();
        }
        return result;
    }
}
