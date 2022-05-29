package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import thymeleaf.models.Company;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public class CompanyRepository {

    private final EntityManager entityManager;

    public CompanyRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Transactional
    public List<Company> findAll() {
        return entityManager
                .createQuery("select c from Company c", Company.class)
                .getResultList();
    }

    @Transactional
    public void save(Company company) {
        entityManager.getTransaction().begin();
        entityManager.persist(company);
        entityManager.getTransaction().commit();
//        entityManager.close();
    }

    @Transactional
    public Company findById(UUID companyId) {
        return entityManager.find(Company.class, companyId);
    }

    @Transactional
    public void update(UUID id, Company company){
        Company company1 = findById(id);
        company1.setCompanyName(company.getCompanyName());
        company1.setLocatedCountry(company.getLocatedCountry());
        entityManager.merge(company1);
    }

    @Transactional
    public void deleteById(UUID companyId) {
        entityManager.remove(entityManager.find(Company.class, companyId));
    }
}
