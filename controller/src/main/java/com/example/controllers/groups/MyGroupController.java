package com.example.controllers.groups;

import com.example.localdatabase.GroupsDatabase;
import com.example.groups.Group;
import com.example.search.SearchFromDatabase;
import com.example.users.Administrator;
import com.example.users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


@WebServlet(urlPatterns = "/mygroupspage")
public class MyGroupController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");

        List<String> groupsName = new ArrayList<>();
        for (Map.Entry<UUID, Group> uuidGroupEntry : GroupsDatabase.getInstance().entrySet()) {
            if (user instanceof Administrator) {
                groupsName.add(uuidGroupEntry.getValue().getGroupName());
            } else  {
                for (Group group : SearchFromDatabase.findUserFromGroupDatabase((User) user)) {
                    groupsName.add(group.getGroupName());
                }
            }
        }

        if (groupsName.isEmpty()){
            session.setAttribute("notFoundGroup", "You are not a member of any group!");
        }

        session.setAttribute("myGroupNamesListResult", groupsName);

        resp.sendRedirect("/new/mygroups");
    }


}
