package com.example.controllers.groups;

import com.example.controllerservice.groups.MyGroupService;
import com.example.users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/mygroupspage")
public class MyGroupController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        final List<String> groupNameList = MyGroupService.getGroupNameList(user);

        if (groupNameList.isEmpty()) {
            req.setAttribute("notFoundGroup", "You are not a member of any group!");
        }

        req.setAttribute("myGroupNamesListResult", groupNameList);

        getServletContext().getRequestDispatcher("/mygroups").forward(req, resp);
    }


}
