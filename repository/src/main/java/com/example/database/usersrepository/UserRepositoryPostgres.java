package com.example.database.usersrepository;

import com.example.database.DataSource;
import com.example.users.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public class UserRepositoryPostgres {
    Logger log = LoggerFactory.getLogger(UserRepositoryPostgres.class);

    private final String SELECT_ROLE_FROM_USERS_BY_LOGIN = "SELECT users.id, users.login, roles.role FROM users \n" +
            "INNER JOIN roles ON users.role = roles.id \n" +
            "WHERE users.login = ?;";

    private final String SELECT_ROLE_FROM_USERS_BY_ID = "SELECT users.id, users.login, roles.role FROM users \n" +
            "INNER JOIN roles ON users.role = roles.id \n" +
            "WHERE users.id = ?;";

    private volatile static UserRepositoryPostgres instance;

    public static UserRepositoryPostgres getInstance() {
        if (instance == null) {
            synchronized (UserRepositoryPostgres.class) {
                if (instance == null) {
                    instance = new UserRepositoryPostgres();
                }
            }
        }
        return instance;
    }

    public Optional<? extends User> find(Integer id) {
        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ROLE_FROM_USERS_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                final String userRole = rs.getString("role");
                final int userId = rs.getInt("id");

                if (userRole.equalsIgnoreCase(Roles.ADMIN.getRole())) {
                    return AdministratorRepositoryPostgres.getInstance().find(userId);
                } else if (userRole.equalsIgnoreCase(Roles.TRAINER.getRole())) {
                    return TrainerRepositoryPostgres.getInstance().find(userId);
                } else {
                    return StudentRepositoryPostgres.getInstance().find(userId);
                }
            }

        } catch (SQLException sqlException) {
            log.error("Error checking User role from database");
        }
        return Optional.empty();
    }

    public Optional<? extends User> getUserByLogin(String login) {
        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ROLE_FROM_USERS_BY_LOGIN)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                final String userRole = rs.getString("role");
                final int userId = rs.getInt("id");

                if (userRole.equalsIgnoreCase(Roles.ADMIN.getRole())) {
                    return AdministratorRepositoryPostgres.getInstance().find(userId);
                } else if (userRole.equalsIgnoreCase(Roles.TRAINER.getRole())) {
                    return TrainerRepositoryPostgres.getInstance().find(userId);
                } else {
                    return StudentRepositoryPostgres.getInstance().find(userId);
                }
            }
        } catch (SQLException sqlException) {
            log.error("Error checking User role from database");
        }
        return Optional.empty();
    }
}
