package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import thymeleaf.models.Group;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public class GroupRepository {

    private final EntityManager entityManager;

    public GroupRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Transactional
    public List<Group> findAll() {
        return entityManager
                .createQuery("select g from Group g", Group.class)
                .getResultList();
    }

    @Transactional
    public void save(Group group) {
        entityManager.getTransaction().begin();
        entityManager.persist(group);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public Group findById(UUID groupId) {
        return entityManager.find(Group.class, groupId);
    }

    @Transactional
    public void update(UUID id, Group group){
        Group group1 = findById(id);
        group1.setGroupName(group.getGroupName());
        group1.setDateOfStart(group.getDateOfStart());
        entityManager.merge(group1);
    }

    @Transactional
    public void deleteById(UUID groupId) {
        entityManager.remove(entityManager.find(Group.class, groupId));
    }
}
