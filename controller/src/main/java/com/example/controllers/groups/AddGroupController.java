package com.example.controllers.groups;

import com.example.controllerservice.groups.AddGroupService;
import com.example.users.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
@RequestMapping("/addgroup")
@AllArgsConstructor
public class AddGroupController {

    private AddGroupService addGroupService;

    @GetMapping
    public ModelAndView showAddGroupPage() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addgrouppage");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView addGroup(@RequestParam(name = "groupName") String groupName,
                                 @RequestParam(name = "groupTrainer") String groupTrainer,
                                 @RequestParam(name = "groupUser") String groupUser) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addgrouppage");

        if (!addGroupService.checkGroupName(groupName)) {
            String splitter = " ";
            Set<Student> userList = addGroupService.getListOfUniqueUsersFromString(groupUser, splitter);

            boolean resultAddGroup = addGroupService.addNewGroup(modelAndView, groupName, groupTrainer, userList);

            if (resultAddGroup) {
                modelAndView.addObject("goodAddGroup", "The group has been successfully created! \n" +
                        "Added students: " + userList.size());
            }
        } else {
            modelAndView.addObject("badAddGroup", "The group name is already taken!");
        }

        return modelAndView;
    }
}
