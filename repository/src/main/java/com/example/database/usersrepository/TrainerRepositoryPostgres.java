package com.example.database.usersrepository;

import com.example.database.DataSource;
import com.example.groups.Group;
import com.example.users.Trainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class TrainerRepositoryPostgres extends AbstractRepositoryPostgres<Trainer> {
    Logger log = LoggerFactory.getLogger(TrainerRepositoryPostgres.class);

    private final String SAVE_STUDENT_SQL = "INSERT INTO users (login, password, full_name, age, role) "
            + "VALUES (?, ?, ?, ?, 2)";
    private final String WHERE_ROLE_SQL = " WHERE role=2;";
    private final String SELECT_SALARY_LIST = "SELECT * FROM trainer_salary WHERE trainer_id = ?;";
    private final String SELECT_AVG_SALARY = "SELECT AVG(salary) FROM trainer_salary WHERE trainer_id = ?;";
    private final String ADD_SALARY = "INSERT INTO trainer_salary (trainer_id, salary) VALUES (?, ?);";

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
                    .withRole(getRoleByID(rs.getInt("id")))
                    .withSalaryList(getSalaryList(rs.getInt("id"))));
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

    public List<Integer> getSalaryList(Integer id) {
        List<Integer> result = new ArrayList<>();
        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_SALARY_LIST)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getInt("salary"));
            }
        } catch (SQLException sqlException) {
            log.error("Error added salaryList from trainer_salary");
        } catch (NoSuchElementException noSuchElementException) {
            log.error("Error noSuchElementException from trainer_salary");
        }
        return result;
    }

    public BigDecimal getAverageSalary(Integer id) {
        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_AVG_SALARY)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBigDecimal("avg");
            }
        } catch (SQLException sqlException) {
            log.error("Error AVG from trainer_salary");
        }
        return BigDecimal.valueOf(0);
    }

    public boolean addSalary(Integer id, Integer salary) {
        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(ADD_SALARY)) {
            ps.setInt(1, id);
            ps.setInt(2, salary);
            ps.executeUpdate();
            return true;
        } catch (SQLException sqlException) {
            log.error("Error AVG from trainer_salary");
        }
        return false;
    }

}
