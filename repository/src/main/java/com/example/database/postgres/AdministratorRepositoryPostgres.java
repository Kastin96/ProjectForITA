package com.example.database.postgres;

import com.example.users.Administrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdministratorRepositoryPostgres extends AbstractRepositoryPostgres<Administrator> {
    Logger log = LoggerFactory.getLogger(AdministratorRepositoryPostgres.class);

    private final String SAVE_STUDENT_SQL = "INSERT INTO users (login, password, role) "
            + "VALUES (?, ?, 1)";
    private final String WHERE_ROLE_SQL = " WHERE role=1;";

    private static volatile AdministratorRepositoryPostgres instance;

    public static AdministratorRepositoryPostgres getInstance() {
        if (instance == null) {
            synchronized (AdministratorRepositoryPostgres.class) {
                if (instance == null) {
                    instance = new AdministratorRepositoryPostgres();
                }
            }
        }
        return instance;
    }

    @Override
    protected List<Administrator> getPersons(ResultSet rs) throws SQLException {
        List<Administrator> result = new ArrayList<>();
        while (rs.next()) {
            result.add(Administrator.builder()
                    .id(rs.getInt("id"))
                    .login(rs.getString("login"))
                    .password(rs.getString("password"))
                    .role(getRoleByID(rs.getInt("id")))
                    .build());
        }
        return result;
    }

    @Override
    protected void prepareForSave(Administrator entity, PreparedStatement ps) throws SQLException {
        ps.setString(1, entity.getLogin().toLowerCase(Locale.ROOT));
        ps.setString(2, entity.getPassword().toLowerCase(Locale.ROOT));
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
