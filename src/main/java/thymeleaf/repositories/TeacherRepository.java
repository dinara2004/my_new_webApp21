package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import thymeleaf.models.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public class TeacherRepository {

    private final EntityManager entityManager;

    public TeacherRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public List<Teacher> findAll() {
        return entityManager
                .createQuery("select t from Teacher t", Teacher.class)
                .getResultList();
    }

    public void save(Teacher teacher) {
        entityManager.getTransaction().begin();
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
    }

    public Teacher findById(UUID teacherId) {
        return entityManager.find(Teacher.class, teacherId);
    }

    public void update(Teacher teacher, UUID id) {
        Teacher teacher1 = findById(id);
        teacher1.setFirstName(teacher.getFirstName());
        teacher1.setEmail(teacher.getEmail());
        teacher1.setLastName(teacher.getLastName());
        entityManager.persist(teacher1);
    }

    public List<Teacher> findAllTeachersById(UUID id) {

        List<Teacher> teachers = entityManager.createQuery
                        ("select t from Teacher t where t.course.id=?1", Teacher.class).
                setParameter(1, id).
                getResultList();

        return teachers;
    }
}