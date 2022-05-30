package thymeleaf.services;

import org.springframework.stereotype.Service;
import thymeleaf.models.Course;
import thymeleaf.models.Group;
import thymeleaf.repositories.GroupRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

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

    @Transactional
    public Group findById(UUID id){
        return groupRepository.findById(id);
    }

    @Transactional
    public void update(Group group, UUID id) {
        groupRepository.update(group, id);
    }
}
