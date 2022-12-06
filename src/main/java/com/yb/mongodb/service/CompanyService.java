package com.yb.mongodb.service;

import com.yb.mongodb.model.Company;
import com.yb.mongodb.model.CompanyDTO;
import com.yb.mongodb.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyService {
    private CompanyRepository companyRepository;
    public List<CompanyDTO> findAll() {
        return  companyRepository.findAll(Sort.by("id"))
                .stream()
                .map(company -> mapToDTO(company, new CompanyDTO()))
                .collect(Collectors.toList());
    }

    public CompanyDTO get(String id) {
        return companyRepository.findById(id)
                .map(company -> mapToDTO(company, new CompanyDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String create(CompanyDTO companyDTO) {
        Company company = new Company();
        mapToEntity(companyDTO, company);
        company.setId(companyDTO.getId());
        return companyRepository.save(company).getId();
    }
    public void update(String id, CompanyDTO companyDTO) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(companyDTO, company);
        companyRepository.save(company);
    }
    public void delete(String id) {
        companyRepository.deleteById(id);
    }

    // MAPPER ISLEMLERI
    private CompanyDTO mapToDTO(Company company, CompanyDTO companyDTO) {
        companyDTO.setId(company.getId());
        companyDTO.setName(company.getName());
        return companyDTO;
    }
    private Company mapToEntity(CompanyDTO companyDTO, Company company) {
        company.setName(companyDTO.getName());
        return company;
    }
}
