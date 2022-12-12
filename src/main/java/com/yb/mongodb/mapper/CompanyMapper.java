package com.yb.mongodb.mapper;

import com.yb.mongodb.model.Company;
import com.yb.mongodb.model.dto.CompanyDTO;

public interface CompanyMapper {
    Company mapToEntity(CompanyDTO companyDTO, Company company);
    CompanyDTO mapToDTO(Company company, CompanyDTO companyDTO);
}
