package com.example.controllerservice.lifecycle;

import com.example.database.AdministratorRepositoryPostgres;
import com.example.users.Administrator;

public class InitAdmin {

    public static void init(String login, String password) {
        AdministratorRepositoryPostgres.getInstance()
                .save(new Administrator()
                        .withLogin(login)
                        .withPassword(password));
    }

    public static void remove(String login) {
        AdministratorRepositoryPostgres.getInstance()
                .remove(AdministratorRepositoryPostgres
                        .getInstance()
                        .getPersonByLogin(login)
                        .get()
                        .getId());
    }
}
