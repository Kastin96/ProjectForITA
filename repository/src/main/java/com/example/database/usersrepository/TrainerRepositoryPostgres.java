package com.example.database.usersrepository;

import com.example.users.Trainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public class TrainerRepositoryPostgres extends AbstractRepositoryPostgres<Trainer> {
    Logger log = LoggerFactory.getLogger(TrainerRepositoryPostgres.class);

    private final String SAVE_STUDENT_SQL = "insert into users (login, password, full_name, age, role) "
            + "values (?, ?, ?, ?, 2)";
    private final String WHERE_ROLE_SQL = " WHERE role=2;";

    private static volatile TrainerRepositoryPostgres instance;

    public static TrainerRepositoryPostgres getInstance() {
        if (instance == null) {
            synchronized (TrainerRepositoryPostgres.class) {
                if (instance == null) {
                    instance = new TrainerRepositoryPostgres();
                }
            }
        }
        return instance;
    }

    @Override
    protected List<Trainer> getPersons(ResultSet rs) throws SQLException {
        List<Trainer> result = new ArrayList<>();
        while (rs.next()) {
            result.add(new Trainer()
                    .withId(rs.getInt("id"))
                    .withLogin(rs.getString("login"))
                    .withPassword(rs.getString("password"))
                    .withFullName(rs.getString("full_name"))
                    .withAge(rs.getInt("age"))
                    .withRole(getRoleByID(rs.getInt("id"))));
        }
        return result;
    }

    @Override
    protected void prepareForSave(Trainer entity, PreparedStatement ps) throws SQLException {
        ps.setString(1, entity.getLogin().toLowerCase(Locale.ROOT));
        ps.setString(2, entity.getPassword().toLowerCase(Locale.ROOT));
        ps.setString(3, entity.getFullName().toLowerCase(Locale.ROOT));
        ps.setInt(4, entity.getAge());
    }

    @Override
    protected String getSaveSql() {
        return SAVE_STUDENT_SQL;
    }

    @Override
    protected String getRoleSql() {
        return WHERE_ROLE_SQL;
    }
}
