package com.example.controllerservice.basicuser;

import com.example.database.usersrepository.AdministratorRepositoryHibernate;
import com.example.database.usersrepository.BasicUserRepositoryHibernate;
import com.example.database.usersrepository.Roles;
import com.example.database.usersrepository.StudentRepositoryHibernate;
import com.example.database.usersrepository.TrainerRepositoryHibernate;
import com.example.users.BasicUser;
import com.example.users.User;

import javax.persistence.NoResultException;
import java.util.Optional;

public class BasicUserService {

    public static Optional<? extends User> findUserWithRoleByLogin(String login){
       try {
           final Optional<BasicUser> basicUser = BasicUserRepositoryHibernate.getInstance().findByLogin(login);
           if (basicUser.isPresent()){
               final BasicUser user = basicUser.get();

               if (user.getRoleNumber().equals(Roles.ADMIN.getRoleNumber())){
                   return AdministratorRepositoryHibernate.getInstance().find(user.getId());
               } else if (user.getRoleNumber().equals(Roles.TRAINER.getRoleNumber())){
                   return TrainerRepositoryHibernate.getInstance().find(user.getId());
               } else {
                   return StudentRepositoryHibernate.getInstance().find(user.getId());
               }
           }
       } catch (NoResultException ignored){
       }
        return Optional.empty();
    }
}
