package com.example.controllers.groups;

import com.example.controllerservice.groups.MyGroupService;
import com.example.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(path = "/mygroups")
public class MyGroupController {
    @Autowired
    private MyGroupService myGroupService;


    @GetMapping()
    public ModelAndView showMyGroups(HttpSession session) {
        final ModelAndView modelAndView = new ModelAndView();
        final User user = (User) session.getAttribute("user");
        modelAndView.setViewName("mygrouppage");

        final List<String> groupNameList = myGroupService.getGroupNameListByHibernate(user);
        if (groupNameList.isEmpty()) {
            modelAndView.addObject("notFoundGroup", "You are not a member of any group!");
        } else {
            modelAndView.addObject("myGroupNamesListResult", groupNameList);
        }

        return modelAndView;
    }


}
