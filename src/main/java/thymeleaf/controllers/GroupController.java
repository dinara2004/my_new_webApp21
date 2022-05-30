package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.models.Group;
import thymeleaf.services.GroupService;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/update/{id}")
    public String editGroup(Model model, @PathVariable("id") UUID id){
        model.addAttribute("group", groupService.findById(id));
        return "groups/updateGroup";
    }


    @PostMapping("/{id}")
    public String updateGroup(@ModelAttribute("group") Group group,
                               @PathVariable("id") UUID id){
        groupService.update(group, id);
        return "redirect:/api/groups";
    }
}
