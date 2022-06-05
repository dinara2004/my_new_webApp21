package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.models.Course;
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

    @GetMapping("/findBy/{id}") // /find/by/34895703985702938450
    public String findByCourseId(@PathVariable UUID id, Model model) {

        List<Group> groups = groupService.findAllGroupsById(id);

        model.addAttribute("groups", groups);
        model.addAttribute("courseId", id);

        return "/groups/allGroups";
    }

//    @GetMapping
//    public String findAll(Model model) {
//
//        model.addAttribute("group", groupService.findAllGroups());
//
//        return "/groups/allGroups";
//    }

    @GetMapping("/save/{id}")
    public String saveGroupPage(Model model, @PathVariable UUID id) {

        model.addAttribute("emptyGroup", new Group());

        model.addAttribute("id", id);

        return "/groups/saveGroupPage";
    }

    @PostMapping("/save/{id}")
    public String saveGroup(Group group,
                             @PathVariable("id") UUID id) {

        groupService.save(group, id);

        return "redirect:/api/groups/findBy/" + id;
    }

    @GetMapping("/update/{id}")
    public String editGroup(Model model, @PathVariable UUID id){
        Group group = groupService.findById(id);
        model.addAttribute("group", group);
        return "groups/updateGroup";
    }


    @PostMapping("/update/{id}")
    public String updateGroup(Group group,
                               @PathVariable UUID id){
        Group byId = groupService.findById(id);
        UUID id1 = byId.getCourses().get(0).getId();
        groupService.update(group, id);
        return "redirect:/api/groups/findBy/" + id1;
    }
}
