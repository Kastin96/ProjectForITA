package com.example.repositoryaccess;

import com.example.dao.TrainerRepository;
import com.example.users.Trainer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TrainerServiceImpl implements TrainerService{
    private TrainerRepository trainerRepository;

    @Override
    public Optional<Trainer> find(Integer id) {
        return trainerRepository.find(id);
    }

    @Override
    public List<Trainer> findAll() {
        return trainerRepository.findAll();
    }

    @Override
    public boolean save(Trainer entity) {
        return trainerRepository.save(entity);
    }

    @Override
    public void remove(Integer id) {
        trainerRepository.remove(id);
    }
}
