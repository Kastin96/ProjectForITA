package com.example.database.postgres;

import com.example.users.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StudentRepositoryPostgres extends AbstractRepositoryPostgres<Student> {
    Logger log = LoggerFactory.getLogger(StudentRepositoryPostgres.class);

    private final String SAVE_STUDENT_SQL = "INSERT INTO users (login, password, full_name, age, role) "
            + "VALUES (?, ?, ?, ?, 3)";
    private final String WHERE_ROLE_SQL = " WHERE role=3;";

    private static volatile StudentRepositoryPostgres instance;

    public static StudentRepositoryPostgres getInstance() {
        if (instance == null) {
            synchronized (StudentRepositoryPostgres.class) {
                if (instance == null) {
                    instance = new StudentRepositoryPostgres();
                }
            }
        }
        return instance;
    }

    @Override
    protected List<Student> getPersons(ResultSet rs) throws SQLException {
        List<Student> result = new ArrayList<>();
        while (rs.next()) {
            result.add(Student.builder()
                    .id(rs.getInt("id"))
                    .login(rs.getString("login"))
                    .password(rs.getString("password"))
                    .fullName(rs.getString("full_name"))
                    .age(rs.getInt("age"))
                    .role(getRoleByID(rs.getInt("id")))
                    .build());
        }
        return result;
    }

    @Override
    protected void prepareForSave(Student entity, PreparedStatement ps) throws SQLException {
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
