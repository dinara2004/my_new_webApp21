package thymeleaf.services;

import org.springframework.stereotype.Service;
import thymeleaf.models.Group;
import thymeleaf.repositories.GroupRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Transactional
    public List<Group> findAllGroups() {
        return groupRepository.findAll();
    }

    @Transactional
    public void save(Group group) {
        System.out.println(group.getGroupName());
        groupRepository.save(group);
        System.out.println("group successfully saved!");
    }
}
