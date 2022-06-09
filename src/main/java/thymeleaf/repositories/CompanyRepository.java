package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import thymeleaf.models.Company;
import thymeleaf.models.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public class CompanyRepository {

    private final EntityManager entityManager;

    public CompanyRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public List<Company> findAll() {
        return entityManager
                .createQuery("select c from Company c", Company.class)
                .getResultList();
    }

    public void save(Company company) {
        entityManager.getTransaction().begin();
        entityManager.persist(company);
        entityManager.getTransaction().commit();
    }

    public Company findById(UUID companyId) {
        return entityManager.find(Company.class, companyId);
    }

    public void update(Company company, UUID id){
        Company company1 = findById(id);
        company1.setCompanyName(company.getCompanyName());
        company1.setLocatedCountry(company.getLocatedCountry());
        entityManager.persist(company1);
    }


    public void deleteById(UUID companyId) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(findById(companyId));
        transaction.commit();
    }

    public void save(Course course, UUID id) {

        final EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        final Company company = entityManager.find(Company.class, id);

        course.setCompany(company);

        company.setCourse(course);

        entityManager.persist(course);

        transaction.commit();
    }
}
