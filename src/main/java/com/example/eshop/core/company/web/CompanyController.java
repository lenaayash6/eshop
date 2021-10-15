package com.example.eshop.core.company.web;

import com.example.eshop.core.company.Company;
import com.example.eshop.core.company.CompanyService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@EnableAutoConfiguration
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CompanyView getCompany(@PathVariable Long id) {
        return service.getCompany(id);
    }

    @GetMapping
    @ResponseBody
    public Page<CompanyView> getAllCompanies(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllCompanies(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CompanyView create(@RequestBody @Valid CompanyBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public CompanyView updateCompany(@PathVariable(name = "id") Long id,
                                     @RequestBody @Valid CompanyBaseReq req) {
        Company company = service.findCompanyOrThrow(id);
        return service.update(company, req);
    }

}
