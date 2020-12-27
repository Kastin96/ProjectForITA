package com.example.controllers.groups;

import com.example.database.GroupsDatabase;
import com.example.groups.Group;
import com.example.search.SearchFromDatabase;
import com.example.users.Trainer;
import com.example.users.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


@WebServlet(urlPatterns = "/addgrouppage")
public class AddGroupController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String groupName = req.getParameter("groupName");
        String groupTrainer = req.getParameter("groupTrainer");
        String groupUser = req.getParameter("groupUser");

        HttpSession session = req.getSession();
        session.removeAttribute("goodAddGroup");
        session.removeAttribute("badAddGroup");

        if (!checkGroupName(groupName)) {
            String splitter = " ";
            List<User> userList = getListOfUniqueUsersFromString(groupUser, splitter);

            boolean resultAddGroup = addNewGroup(session, groupName, groupTrainer, userList);
            if (resultAddGroup) {
                session.setAttribute("goodAddGroup", "The group has been successfully created! \n" +
                        "Added students: " + userList.size());
            }

        } else {
            session.setAttribute("badAddGroup", "The group name is already taken!");
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addgroup");
        requestDispatcher.forward(req, resp);

    }

    private boolean checkGroupName(String groupName) {
        for (Map.Entry<UUID, Group> uuidGroup : GroupsDatabase.getInstance().entrySet()) {
            if (uuidGroup.getValue().getGroupName().equalsIgnoreCase(groupName)) {
                return true;
            }
        }
        return false;
    }

    private List<User> getListOfUniqueUsersFromString(String groupUser, String splitter) {
        Set<User> userSet = new HashSet<>();

        for (String splittedUser : groupUser.split(splitter)) {
            User user = SearchFromDatabase.findUserFromUserDatabase(splittedUser);

            if (user != null) {
                userSet.add(user);
            }
        }

        return List.copyOf(userSet);
    }

    private boolean addNewGroup(HttpSession session, String groupName, String groupTrainer, List<User> userList) {
        User trainer = SearchFromDatabase.findUserFromUserDatabase(groupTrainer);

        if (trainer != null) {
            if (trainer instanceof Trainer) {
                if (SearchFromDatabase.findFromGroupDatabase(groupTrainer)) {
                    session.setAttribute("badAddGroup", "The Trainer is busy!");
                } else {
                    if (!userList.isEmpty()) {
                        Group group = new Group(groupName, (Trainer) trainer, userList);
                        GroupsDatabase.getInstance().put(group.getId(), group);

                        return true;
                    } else {
                        session.setAttribute("badAddGroup", "Something went wrong: try again!" +
                                "No user found!");
                    }
                }
            } else {
                session.setAttribute("badAddGroup", "The trainer is incorrect!");
            }
        } else {
            session.setAttribute("badAddGroup", "The trainer is incorrect!");
        }
        return false;
    }

}
