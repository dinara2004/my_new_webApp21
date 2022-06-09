package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.models.Company;
import thymeleaf.services.CompanyService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("api/companies")
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

        return "redirect:/api/companies/";
    }

    @GetMapping("/update/{id}")
    public String editCompany(Model model, @PathVariable("id") UUID id){
        model.addAttribute("company", companyService.findById(id));
        return "companies/updateCompany";
    }


    @PostMapping("/update/{id}")
    public String updateCompany(@ModelAttribute("company") Company company,
                                @PathVariable("id") UUID id){
        companyService.update(company, id);
        return "redirect:/api/companies";
    }

    @GetMapping("/delete/{companyId}")
    public String deleteCompany(@PathVariable("companyId") UUID id){
        companyService.deleteById(id);
        return "redirect:/api/companies";
    }
}

//    @PutMapping("/findBy/{id}")
//    public String findCompanyById(@PathVariable UUID id) {
//        companyService.findById(id);
//}