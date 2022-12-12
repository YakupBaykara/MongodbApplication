package com.yb.mongodb.mapper.impl;

import com.yb.mongodb.mapper.CompanyMapper;
import com.yb.mongodb.model.Company;
import com.yb.mongodb.model.dto.CompanyDTO;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapperImpl implements CompanyMapper {
    @Override
    public Company mapToEntity(CompanyDTO companyDTO, Company company) {
        company.setName(companyDTO.getName());
        return company;
    }

    @Override
    public CompanyDTO mapToDTO(Company company, CompanyDTO companyDTO) {
        companyDTO.setId(company.getId());
        companyDTO.setName(company.getName());
        return companyDTO;
    }
}
