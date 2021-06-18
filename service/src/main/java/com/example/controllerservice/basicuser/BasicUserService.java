package com.example.controllerservice.basicuser;

import com.example.database.usersrepository.AdministratorRepositoryHibernate;
import com.example.database.usersrepository.BasicUserRepositoryHibernate;
import com.example.database.usersrepository.Roles;
import com.example.database.usersrepository.StudentRepositoryHibernate;
import com.example.database.usersrepository.TrainerRepositoryHibernate;
import com.example.users.BasicUser;
import com.example.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.Optional;

@Service
public class BasicUserService {

    @Autowired
    private AdministratorRepositoryHibernate adminRepository;
    @Autowired
    private TrainerRepositoryHibernate trainerRepository;
    @Autowired
    private StudentRepositoryHibernate studentRepository;
    @Autowired
    private BasicUserRepositoryHibernate basicUserRepository;

    public Optional<? extends User> findUserWithRoleByLogin(String login) {
        try {
            final Optional<BasicUser> basicUser = basicUserRepository.findByLogin(login);
            if (basicUser.isPresent()) {
                final BasicUser user = basicUser.get();

                if (user.getRoleNumber().equals(Roles.ADMIN.getRoleNumber())) {
                    return adminRepository.find(user.getId());
                } else if (user.getRoleNumber().equals(Roles.TRAINER.getRoleNumber())) {
                    return trainerRepository.find(user.getId());
                } else {
                    return studentRepository.find(user.getId());
                }
            }
        } catch (NoResultException ignored) {
        }
        return Optional.empty();
    }
}
