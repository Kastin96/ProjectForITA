package com.example.repositoryaccess;

import com.example.dao.AdministratorRepository;
import com.example.users.Administrator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AdministratorServiceImpl implements AdministratorService{
    private AdministratorRepository administratorRepository;

    @Override
    public Optional<Administrator> find(Integer id) {
        return administratorRepository.find(id);
    }

    @Override
    public List<Administrator> findAll() {
        return administratorRepository.findAll();
    }

    @Override
    public boolean save(Administrator entity) {
        return administratorRepository.save(entity);
    }

    @Override
    public void remove(Integer id) {
        administratorRepository.remove(id);
    }
}
