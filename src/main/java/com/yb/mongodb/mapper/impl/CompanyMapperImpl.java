package com.yb.mongodb.mapper.impl;

import com.yb.mongodb.mapper.CompanyMapper;
import com.yb.mongodb.model.Company;
import com.yb.mongodb.model.dto.CompanyDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CompanyMapperImpl implements CompanyMapper {
    @Override
    public Company mapToEntity(CompanyDTO companyDTO, Company company) {
        company.setName(companyDTO.getName());
        company.setPhoneNumber(companyDTO.getPhoneNumber());
        company.setEmail(companyDTO.getEmail());
        company.setCreatedAt(LocalDateTime.now());
        return company;
    }

    @Override
    public CompanyDTO mapToDTO(Company company, CompanyDTO companyDTO) {
        companyDTO.setId(company.getId());
        companyDTO.setName(company.getName());
        companyDTO.setPhoneNumber(company.getPhoneNumber());
        companyDTO.setEmail(company.getEmail());
        companyDTO.setCreatedAt(company.getCreatedAt());
        return companyDTO;
    }
}
