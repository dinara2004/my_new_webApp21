package thymeleaf.services;

import org.springframework.stereotype.Service;
import thymeleaf.models.Course;
import thymeleaf.models.Group;
import thymeleaf.repositories.CourseRepository;
import thymeleaf.repositories.GroupRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    public GroupService(GroupRepository groupRepository, CourseRepository courseRepository) {
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public List<Group> findAllGroups() {
        return groupRepository.findAll();
    }

    @Transactional
    public void save(Group group, UUID courseId) {
        Course byId = courseRepository.findById(courseId);
        group.setCourse(byId);
        byId.setGroup(group);
        groupRepository.save(group);
    }

    @Transactional
    public Group findById(UUID id){
        return groupRepository.findById(id);
    }

    @Transactional
    public void update(Group group, UUID id) {
        groupRepository.update(group, id);
    }

    public List<Group> findAllGroupsById(UUID id) {
        return groupRepository.findAllGroupsById(id);
    }
}
