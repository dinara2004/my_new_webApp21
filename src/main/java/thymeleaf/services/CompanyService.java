package thymeleaf.services;

import org.springframework.stereotype.Service;
import thymeleaf.models.Company;
import thymeleaf.repositories.CompanyRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Transactional
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    @Transactional
    public void save(Company company) {
        System.out.println(company.getCompanyName());
        companyRepository.save(company);
        System.out.println("company successfully saved!");
    }

    @Transactional
    public Company findById(UUID id){
        return companyRepository.findById(id);
    }

    public void update(Company company, UUID id) {
        companyRepository.update(company,id);
    }


    @Transactional
    public void deleteById(UUID id){
        companyRepository.deleteById(id);
    }
}
