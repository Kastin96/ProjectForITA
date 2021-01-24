package com.example.database.usersrepository;

import com.example.database.DataSource;
import com.example.database.Repository;
import com.example.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public abstract class AbstractRepositoryPostgres<T extends User> implements Repository<T> {
    Logger log = LoggerFactory.getLogger(AbstractRepositoryPostgres.class);

    private final String SELECT_ALL_FROM_USERS = "SELECT * FROM users";
    private final String REMOVE_FROM_USERS_BY_ID = "DELETE FROM users WHERE id=?";
    private final String SELECT_ROLE_FROM_USERS_BY_ID = "SELECT users.id, roles.role FROM users \n" +
            "INNER JOIN roles ON users.role = roles.id \n" +
            "WHERE users.id = ?;";
    private final String SELECT_FROM_USERS_BY_ID = "SELECT * FROM users WHERE id= ?";
    private final String SELECT_USER_BY_LOGIN = "select * from users where login = ?;";

    protected abstract String getSaveSql();

    protected abstract String getRoleSql();

    protected abstract void prepareForSave(T entity, PreparedStatement ps) throws SQLException;

    protected abstract List<T> getPersons(ResultSet rs) throws SQLException;

    @Override
    public Optional<T> find(Integer id) {
        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_FROM_USERS_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            final List<T> persons = getPersons(rs);
            return persons.stream().findFirst();
        } catch (SQLException sqlException) {
            log.error("Error reading from database");
        }
        return Optional.empty();
    }

    @Override
    public List<T> findAll() {
        final String SQL = SELECT_ALL_FROM_USERS + getRoleSql();

        List<T> result = new LinkedList<>();
        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            return getPersons(rs);
        } catch (SQLException sqlException) {
            log.error("Error reading from database");
        }
        return result;
    }

    @Override
    public boolean save(T entity) {
        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(getSaveSql())) {
            prepareForSave(entity, ps);
            ps.executeUpdate();
            return true;
        } catch (SQLException sqlException) {
            log.error("Error saving to database");
            return false;
        }
    }

    @Override
    public boolean remove(Integer id) {
        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(REMOVE_FROM_USERS_BY_ID)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException sqlException) {
            log.error("Error removing from database");
            return false;
        }
    }

    protected String getRoleByID(Integer id) {
        String result = null;
        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ROLE_FROM_USERS_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getString("role");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            log.error("Error get Role by ID from database");
        }
        return result;
    }

    public Optional<T> getPersonByLogin(String login) {
        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_LOGIN)) {
            ps.setString(1, login.toLowerCase());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return find(rs.getInt("id"));
            }
        } catch (SQLException sqlException) {
            log.error("Error get Person by Login from database");
        }
        return Optional.empty();
    }
}
