package com.example.database.groupsrepository;

import com.example.database.DataSource;
import com.example.database.Repository;
import com.example.database.usersrepository.TrainerRepositoryPostgres;
import com.example.database.usersrepository.UserRepositoryPostgres;
import com.example.groups.Group;
import com.example.users.Trainer;
import com.example.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public class GroupsRepositoryPostgres implements Repository<Group> {
    Logger log = LoggerFactory.getLogger(GroupsRepositoryPostgres.class);

    private static final String SELECT_All_FROM_GROUP = "SELECT * FROM groups";
    private static final String SELECT_All_FROM_GROUP_BY_TRAINER_ID = "SELECT * FROM groups WHERE trainer_id = ?";
    private static final String SELECT_All_FROM_GROUP_BY_ID = "SELECT * FROM groups WHERE id = ?;";
    private static final String SELECT_All_FROM_GROUP_USER_BY_USER_ID = "SELECT * FROM group_users WHERE user_id = ?;";
    private static final String SAVE_GROUP_SQL = "INSERT INTO groups (group_name, trainer_id) VALUES (?, ?);";
    private static final String SAVE_USER_SET = "INSERT INTO group_users (user_id, group_id) VALUES (?, ?);";
    private static final String SELECT_GROUP_USER_BY_ID = "select * from group_users where group_id = ?;";
    private static final String SELECT_ID_BY_NAME = "SELECT * FROM groups WHERE group_name = ?;";
    private static final String REMOVE_GROUP_BY_ID = "DELETE FROM groups WHERE id = ?;";


    private volatile static GroupsRepositoryPostgres instance;

    public static GroupsRepositoryPostgres getInstance() {
        if (instance == null) {
            synchronized (GroupsRepositoryPostgres.class) {
                if (instance == null) {
                    instance = new GroupsRepositoryPostgres();
                }
            }
        }
        return instance;
    }

    @Override
    public Optional<Group> find(Integer id) {
        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_All_FROM_GROUP_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new Group()
                        .withId(rs.getInt("id"))
                        .withGroupName(rs.getString("group_name"))
                        .withTrainer(getTrainer(rs))
                        .withUserList(getUserSetByGroupId(rs.getInt("id"))));
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            log.error("Error reading Group from database");
        }
        return Optional.empty();
    }

    @Override
    public List<Group> findAll() {
        List<Group> result = new ArrayList<>();
        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_All_FROM_GROUP)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(find(rs.getInt("id")).get());
            }
        } catch (SQLException sqlException) {
            log.error("Error readingAll Group from database");
        }
        return result;
    }

    @Override
    public boolean save(Group entity) {
        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SAVE_GROUP_SQL)) {
            ps.setString(1, entity.getGroupName());
            ps.setInt(2, entity.getTrainer().getId());
            ps.executeUpdate();
            saveUserSet(entity);
            return true;
        } catch (SQLException sqlException) {
            log.error("Error saving Group from database");
        }
        return false;
    }

    @Override
    public boolean remove(Integer id) {
        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(REMOVE_GROUP_BY_ID)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException sqlException) {
            log.error("Error removing Group from database");
        }
        return false;
    }

    private boolean saveUserSet(Group entity) throws SQLException {
        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SAVE_USER_SET)) {
            for (User user : entity.getUserList()) {
                ps.setInt(1, user.getId());
                ps.setInt(2, getIdGroupByName(entity.getGroupName()));
                ps.executeUpdate();
            }
            return true;
        } catch (SQLException sqlException) {
            log.error("Error saveUserSet Group from database");
        }
        return false;
    }

    public Set<User> getUserSetByGroupId(Integer id) throws SQLException {
        Set<User> userSet = new LinkedHashSet<>();
        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_GROUP_USER_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userSet.add(UserRepositoryPostgres.getInstance()
                        .find(rs.getInt("user_id"))
                        .get());
            }
        } catch (SQLException sqlException) {
            log.error("Error getUserSetByGroupId Group from database");
        }
        return userSet;
    }

    private Trainer getTrainer(ResultSet rs) throws SQLException {
        return TrainerRepositoryPostgres.getInstance()
                .find(rs.getInt("trainer_id"))
                .get();
    }

    public Integer getIdGroupByName(String name) {
        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ID_BY_NAME)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException sqlException) {
            log.error("Error getIdGroupByName Group from database");
        }
        return null;
    }

    public List<Group> getGroupListByUserId(Integer id) {
        List<Group> result = new ArrayList<>();
        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_All_FROM_GROUP_USER_BY_USER_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(find(rs.getInt("group_id")).get());
            }
        } catch (SQLException sqlException) {
            log.error("Error getGroupByUserId Group from database");
        }
        return result;
    }

    public List<Group> getGroupListByTrainerId(Integer id) {
        List<Group> result = new ArrayList<>();
        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_All_FROM_GROUP_BY_TRAINER_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(find(rs.getInt("id")).get());
            }
        } catch (SQLException sqlException) {
            log.error("Error getGroupByTrainerId from database");
        }
        return result;
    }


}
