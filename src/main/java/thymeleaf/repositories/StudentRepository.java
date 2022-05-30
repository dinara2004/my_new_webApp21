package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import thymeleaf.models.Group;
import thymeleaf.models.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public class StudentRepository {

    private final EntityManager entityManager;

    public StudentRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Transactional
    public List<Student> findAll() {
        return entityManager
                .createQuery("select s from Student s", Student.class)
                .getResultList();
    }

    @Transactional
    public void save(Student student) {
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public Student findById(UUID studentId) {
        return entityManager.find(Student.class, studentId);
    }

    @Transactional
    public void update(Student student, UUID id){
        Student student1 = findById(id);
        student1.setName(student.getName());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        entityManager.persist(student1);
    }

    public List<Student> findByCourseId(UUID courseId) {
        return entityManager
                .createQuery("select s from Student s where (select c from Course c where c.id = ?1) member of s.courses", Student.class)
                .setParameter(1, courseId)
                .getResultList();
    }

    @Transactional
    public void deleteById(UUID studentId) {
        entityManager.remove(entityManager.find(Student.class, studentId));
    }
}
