package com.example.controllers.groups;

import com.example.controllerservice.groups.ShowGroupService;
import com.example.groups.Group;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


@WebServlet(urlPatterns = "/showgroupspage")
public class ShowGroupController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        clearSessionAttribute(session);

        String showGroupName = req.getParameter("showGroupName");
        List<String> userNameList;
        try {
            userNameList = ShowGroupService.getUserNameList(showGroupName);

            final Optional<Group> groupByName = ShowGroupService.getGroupByName(showGroupName);

            if (groupByName.isPresent()) {
                final Group group = groupByName.get();
                if (!userNameList.isEmpty()) {
                    session.setAttribute("showGroupUserListName", userNameList);
                }
                session.setAttribute("showGroupName",
                        group.getGroupName());
                session.setAttribute("showGroupTrainerName",
                        group.getTrainer().getLogin());
            }

        } catch (NullPointerException exception) {
            session.setAttribute("notFoundGroupToShow", showGroupName + " - Not found!");
        }

        resp.sendRedirect("/new/mygroups");
    }

    private void clearSessionAttribute(HttpSession session) {
        session.removeAttribute("showGroupUserListName");
        session.removeAttribute("showGroupName");
        session.removeAttribute("showGroupTrainerName");
        session.removeAttribute("notFoundGroupToShow");

    }


}
