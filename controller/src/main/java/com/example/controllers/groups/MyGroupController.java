package com.example.controllers.groups;

import com.example.controllerservice.groups.MyGroupService;
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
        User user = (User) session.getAttribute("user");
        final List<String> groupNameList = MyGroupService.getGroupNameList((User) user);

        if (groupNameList.isEmpty()){
            session.setAttribute("notFoundGroup", "You are not a member of any group!");
        }

        session.setAttribute("myGroupNamesListResult", groupNameList);

        resp.sendRedirect("/new/mygroups");
    }


}
