package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import thymeleaf.models.Group;
import thymeleaf.services.GroupService;

import java.util.List;

@Controller
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @ModelAttribute("groupList")
    public List<Group> findAllGroups() {

        return groupService.findAllGroups();
    }

    @GetMapping
    public String findAll(Model model) {

        model.addAttribute("group", groupService.findAllGroups());

        return "/groups/allGroups";
    }

    @GetMapping("/save")
    public String saveGroupPage(Model model) {

        model.addAttribute("emptyGroup", new Group());

        return "/groups/saveGroupPage";
    }

    @PostMapping("/save")
    public String saveCourse(Group group) {

        System.out.println(group);

        groupService.save(group);

        return "redirect:/api/groups";
    }
}
