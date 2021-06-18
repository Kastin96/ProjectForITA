package com.example.controllers.groups;

import com.example.controllerservice.groups.AddGroupService;
import com.example.users.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


@WebServlet(urlPatterns = "/addgrouppage")
public class AddGroupController extends HttpServlet {
    Logger log = LoggerFactory.getLogger(AddGroupController.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String groupName = req.getParameter("groupName");
        String groupTrainer = req.getParameter("groupTrainer");
        String groupUser = req.getParameter("groupUser");

//        if (!AddGroupService.checkGroupName(groupName)) {
        if (!AddGroupService.checkGroupNameByHibernate(groupName)) {
            String splitter = " ";
//            Set<Student> userList = AddGroupService.getListOfUniqueUsersFromString(groupUser, splitter);
            Set<Student> userList = AddGroupService.getListOfUniqueUsersFromStringByHibernate(groupUser, splitter);

//            boolean resultAddGroup = AddGroupService.addNewGroup(req, groupName, groupTrainer, userList);
            boolean resultAddGroup = AddGroupService.addNewGroupByHibernate(req, groupName, groupTrainer, userList);

            if (resultAddGroup) {
                req.setAttribute("goodAddGroup", "The group has been successfully created! \n" +
                        "Added students: " + userList.size());
            }
        } else {
            req.setAttribute("badAddGroup", "The group name is already taken!");
        }

        getServletContext().getRequestDispatcher("/addgroup").forward(req, resp);
    }
}
