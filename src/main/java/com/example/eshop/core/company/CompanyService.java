package com.example.eshop.core.company;

import com.example.eshop.core.company.converter.CompanyToCompanyViewConverter;
import com.example.eshop.core.company.web.CompanyBaseReq;
import com.example.eshop.core.company.web.CompanyView;
import com.example.eshop.error.EntityNotFoundException;
import com.example.eshop.util.MessageUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {


    private final CompanyRepo companyRepo;
    private final CompanyToCompanyViewConverter companyToCompanyViewConverter;
    private final MessageUtil messageUtil;

    public CompanyService(CompanyRepo companyRepo, CompanyToCompanyViewConverter companyToCompanyViewConverter, MessageUtil messageUtil) {
        this.companyRepo = companyRepo;
        this.companyToCompanyViewConverter = companyToCompanyViewConverter;
        this.messageUtil = messageUtil;
    }


    public Company findCompanyOrThrow(Long id) {
        return companyRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage(" company.NotFound", id)));
    }

    public CompanyView getCompany(Long id) {
        Company company = findCompanyOrThrow(id);
        return companyToCompanyViewConverter.convert(company);
    }

    public CompanyView create(CompanyBaseReq req) {
        Company company = new Company();
        this.prepare(company, req);
        Company companySave = companyRepo.save(company);
        return companyToCompanyViewConverter.convert(companySave);
    }

    public Page<CompanyView> findAllCompanies(Pageable pageable) {
        Page<Company> companies = companyRepo.findAll(pageable);
        List<CompanyView> companyViews = new ArrayList<>();
        companies.forEach(company -> {
            CompanyView companyView = companyToCompanyViewConverter.convert(company);
            companyViews.add(companyView);
        });
        return new PageImpl<>(companyViews, pageable, companies.getTotalElements());
    }

    @Transactional
    public void delete(Long id) {
        try {
            companyRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("company.NotFound", id));
        }
    }

    public CompanyView update(Company company, CompanyBaseReq req) {
        Company newCompany = this.prepare(company, req);
        Company companyForSave = companyRepo.save(newCompany);
        return companyToCompanyViewConverter.convert(companyForSave);
    }

    private Company prepare(Company company, CompanyBaseReq req) {
        company.setName(req.getName());
        company.setAbout(req.getAbout());
        company.setEmail(req.getEmail());
        company.setAddress(req.getAddress());
        company.setLogoUrl(req.getLogoUrl());

        return company;
    }

}
