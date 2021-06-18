package com.example.repositoryaccess;

import com.example.dao.BasicUserRepository;
import com.example.users.BasicUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BasicUserServiceImpl implements BasicUserService{
    private BasicUserRepository basicUserRepository;

    @Override
    public Optional<BasicUser> find(Integer id) {
        return basicUserRepository.find(id);
    }

    @Override
    public List<BasicUser> findAll() {
        return basicUserRepository.findAll();
    }

    @Override
    public boolean save(BasicUser entity) {
        return basicUserRepository.save(entity);
    }

    @Override
    public void remove(Integer id) {
        basicUserRepository.remove(id);
    }

    @Override
    public Optional<BasicUser> findByLogin(String login) {
        return basicUserRepository.findByLogin(login);
    }
}
