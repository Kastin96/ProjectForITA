package com.example.controllerservice.basicuser;

import com.example.repositoryaccess.AdministratorService;
import com.example.repositoryaccess.BasicUserService;
import com.example.repositoryaccess.StudentService;
import com.example.repositoryaccess.TrainerService;
import com.example.database.hibernate.AdministratorRepositoryHibernate;
import com.example.database.hibernate.BasicUserRepositoryHibernate;
import com.example.database.Roles;
import com.example.database.hibernate.StudentRepositoryHibernate;
import com.example.database.hibernate.TrainerRepositoryHibernate;
import com.example.users.BasicUser;
import com.example.users.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DispenserUserWithRole {
    private AdministratorService administratorService;
    private TrainerService trainerService;
    private StudentService studentService;
    private BasicUserService basicUserService;

    public Optional<? extends User> getUserByLogin(String login) {
        try {
            final Optional<BasicUser> basicUser = basicUserService.findByLogin(login);
            if (basicUser.isPresent()) {
                final BasicUser user = basicUser.get();

                if (user.getRoleNumber().equals(Roles.ADMIN.getRoleNumber())) {
                    return administratorService.find(user.getId());
                } else if (user.getRoleNumber().equals(Roles.TRAINER.getRoleNumber())) {
                    return trainerService.find(user.getId());
                } else {
                    return studentService.find(user.getId());
                }
            }
        } catch (NoResultException ignored){
        }

        return Optional.empty();
    }
}
