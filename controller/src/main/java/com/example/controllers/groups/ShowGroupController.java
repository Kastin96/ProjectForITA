package com.example.controllers.groups;

import com.example.controllerservice.groups.ShowGroupService;
import com.example.groups.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/showgroupspage")
public class ShowGroupController {
    @Autowired
    private ShowGroupService showGroupService;

    @PostMapping
    protected ModelAndView showGroupByName(@RequestParam(name = "showGroupName") String groupName) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mygrouppage");

        List<String> userNameList;

        try {
            userNameList = showGroupService.getUserNameListFromHibernate(groupName);
            final Optional<Group> groupByName = showGroupService.getGroupByNameFromHibernate(groupName);

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
