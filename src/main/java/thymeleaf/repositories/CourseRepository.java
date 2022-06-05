package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import thymeleaf.models.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public class CourseRepository {

    private final EntityManager entityManager;

    public CourseRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Transactional
    public List<Course> findAll() {
        return entityManager
                .createQuery("select c from Course c", Course.class)
                .getResultList();
    }

    @Transactional
    public void save(Course course) {
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public Course findById(UUID courseId) {
        return entityManager.find(Course.class, courseId);
    }

    public void update(Course course, UUID id){
        Course course1 = findById(id);
        course1.setName(course.getName());
        course1.setDuration(course.getDuration());
        entityManager.persist(course1);
    }


    @Transactional
    public void deleteById(UUID courseId) {
        entityManager.remove(entityManager.find(Course.class, courseId));
    }


    public List<Course> findAllCoursesById(UUID id) {

        final List<Course> courses = entityManager.createQuery
                        ("select c from Course c where c.company.id = ?1", Course.class)
                .setParameter(1, id)
                .getResultList();

        return courses;
    }
}


