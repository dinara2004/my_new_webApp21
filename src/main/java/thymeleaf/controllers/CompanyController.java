package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import thymeleaf.models.Company;
import thymeleaf.services.CompanyService;

import java.util.List;

@Controller
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @ModelAttribute("companyList")
    public List<Company> findAllCompanies() {

        return companyService.findAllCompanies();
    }

    @GetMapping
    public String findAll(Model model) {

        model.addAttribute("company", companyService.findAllCompanies());

        return "/companies/allCompanies";
    }

    @GetMapping("/save")
    public String saveCompanyPage(Model model) {

        model.addAttribute("emptyCompany", new Company());

        return "/companies/saveCompanyPage";
    }

    @PostMapping("/save")
    public String saveCompany(Company company) {

        System.out.println(company);

        companyService.save(company);

        return "redirect:/api/companies";
    }
}

//    @PutMapping("/findBy/{id}")
//    public String findCompanyById(@PathVariable UUID id) {
//        companyService.findById(id);
//
//}