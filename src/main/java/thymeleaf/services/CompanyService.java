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

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    public void save(Company company) {
        System.out.println(company.getCompanyName());
        companyRepository.save(company);
        System.out.println("company successfully saved!");
    }

    public Company findById(UUID id){
        return companyRepository.findById(id);
    }

    public void update(Company company, UUID id) {
        companyRepository.update(company,id);
    }


    public void deleteById(UUID companyId){
        companyRepository.deleteById(companyId);
    }
}
