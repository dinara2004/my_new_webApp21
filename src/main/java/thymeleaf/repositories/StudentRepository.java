package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import thymeleaf.models.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public class StudentRepository {

    private final EntityManager entityManager;

    public StudentRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public List<Student> findAll() {
        return entityManager
                .createQuery("select s from Student s", Student.class)
                .getResultList();
    }

    public void save(Student student) {
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
    }

    public Student findById(UUID studentId) {
        return entityManager.find(Student.class, studentId);
    }
    public void update(Student student, UUID id){
        Student student1 = findById(id);
        student1.setName(student.getName());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
//        entityManager.persist(student1);
    }

    public List<Student> findAllStudentsById(UUID groupId) {

        final EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        final List<Student> students = entityManager.createQuery
                        ("select s from Student s join Group g on g.id=?1", Student.class).
                setParameter(1, groupId).
                getResultList();

        transaction.commit();

        return students;
    }

    public void deleteById(UUID studentId) {
        entityManager.remove(findById(studentId));
    }
}
