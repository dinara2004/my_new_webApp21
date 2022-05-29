package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import thymeleaf.models.Student;
import thymeleaf.models.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public class TeacherRepository {

    private final EntityManager entityManager;

    public TeacherRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Transactional
    public List<Teacher> findAll() {
        return entityManager
                .createQuery("select t from Teacher t", Teacher.class)
                .getResultList();
    }

    @Transactional
    public void save(Teacher teacher) {
        entityManager.getTransaction().begin();
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public Teacher findById(UUID teacherId) {
        return entityManager.find(Teacher.class, teacherId);
    }

    @Transactional
    public void update(UUID id, Teacher teacher){
        Teacher teacher1 = findById(id);
        teacher1.setFirstName(teacher.getFirstName());
        teacher1.setEmail(teacher.getEmail());
        teacher1.setLastName(teacher.getLastName());
        entityManager.merge(teacher1);
    }

    @Transactional
    public void deleteById(UUID teacherId) {
        entityManager.remove(entityManager.find(Teacher.class, teacherId));
    }
}
