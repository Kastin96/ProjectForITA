package com.example.controllers.groups;

import com.example.controllerservice.groups.ShowGroupService;
import com.example.groups.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@WebServlet(urlPatterns = "/showgroupspage")
public class ShowGroupController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String showGroupName = req.getParameter("showGroupName");
        List<String> userNameList;
        try {
            userNameList = ShowGroupService.getUserNameList(showGroupName);

            final Optional<Group> groupByName = ShowGroupService.getGroupByName(showGroupName);

            if (groupByName.isPresent()) {
                final Group group = groupByName.get();
                if (!userNameList.isEmpty()) {
                    req.setAttribute("showGroupUserListName", userNameList);
                }
                req.setAttribute("showGroupName",
                        group.getGroupName());
                req.setAttribute("showGroupTrainerName",
                        group.getTrainer().getLogin());
            }

        } catch (NullPointerException exception) {
            req.setAttribute("notFoundGroupToShow", showGroupName + " - Not found!");
        }

        getServletContext().getRequestDispatcher("/mygroups").forward(req, resp);
    }


}
