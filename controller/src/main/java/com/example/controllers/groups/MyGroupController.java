package com.example.controllers.groups;

import com.example.controllerservice.groups.MyGroupService;
import com.example.controllerservice.groups.ShowGroupService;
import com.example.groups.Group;
import com.example.users.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/mygroups")
@AllArgsConstructor
public class MyGroupController {

    private MyGroupService myGroupService;
    private ShowGroupService showGroupService;

    @GetMapping()
    public ModelAndView showMyGroups(HttpSession session) {
        final ModelAndView modelAndView = new ModelAndView();
        final User user = (User) session.getAttribute("user");
        modelAndView.setViewName("mygrouppage");

        final List<String> groupNameList = myGroupService.getGroupNameList(user);
        if (groupNameList.isEmpty()) {
            modelAndView.addObject("notFoundGroup", "You are not a member of any group!");
        } else {
            modelAndView.addObject("myGroupNamesListResult", groupNameList);
        }

        return modelAndView;
    }

    @PostMapping
    protected ModelAndView showGroupByName(@RequestParam(name = "showGroupName") String groupName) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mygrouppage");

        List<String> userNameList;

        try {
            userNameList = showGroupService.getUserNameList(groupName);
            final Optional<Group> groupByName = showGroupService.getGroupByName(groupName);

            if (groupByName.isPresent()) {
                final Group group = groupByName.get();
                if (!userNameList.isEmpty()) {
                    modelAndView.addObject("showGroupUserListName", userNameList);
                }
                modelAndView.addObject("showGroupName", group.getGroupName());
                modelAndView.addObject("showGroupTrainerName", group.getTrainer().getLogin());
            }

        } catch (NullPointerException | NoResultException exception) {
            modelAndView.addObject("notFoundGroupToShow", groupName + " - Not found!");
        }
        return modelAndView;
    }


}
