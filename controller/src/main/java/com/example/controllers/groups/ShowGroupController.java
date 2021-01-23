package com.example.controllers.groups;

import com.example.localdatabase.GroupsDatabase;
import com.example.groups.Group;
import com.example.users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@WebServlet(urlPatterns = "/showgroupspage")
public class ShowGroupController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        clearSessionAttribute(session);

        String showGroupName = req.getParameter("showGroupName");
        List<String> userNameList = new ArrayList<>();

        for (Map.Entry<UUID, Group> uuidGroupEntry : GroupsDatabase.getInstance().entrySet()) {
            if (uuidGroupEntry.getValue().getGroupName().equalsIgnoreCase(showGroupName)) {

                for (User user : uuidGroupEntry.getValue().getUserList()) {
//                    userNameList.add(user.getFullName());
                }

                session.setAttribute("showGroupUserListName", userNameList);
                session.setAttribute("showGroupName",
                        uuidGroupEntry.getValue().getGroupName());
                session.setAttribute("showGroupTrainerName",
                        uuidGroupEntry.getValue().getTrainer().getFullName());
            }
        }

        if (userNameList.isEmpty()) {
            session.setAttribute("notFoundGroupToShow", showGroupName + " - Not found!");
        }

        resp.sendRedirect("/new/mygroups");
    }

    private void clearSessionAttribute(HttpSession session){
        session.removeAttribute("showGroupUserListName");
        session.removeAttribute("showGroupName");
        session.removeAttribute("showGroupTrainerName");
        session.removeAttribute("notFoundGroupToShow");

    }


}
