package com.yb.mongodb.mapper.impl;

import com.yb.mongodb.mapper.UserMapper;
import com.yb.mongodb.model.Company;
import com.yb.mongodb.model.User;
import com.yb.mongodb.model.dto.UserDTO;
import com.yb.mongodb.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@AllArgsConstructor
public class UserMapperImpl implements UserMapper {

    private CompanyRepository companyRepository;
    @Override
    public User mapToEntity(UserDTO userDTO, User user) {
        user.setName(userDTO.getName());

        Company company = userDTO.getCompany() == null ? null : companyRepository.findById(userDTO.getCompany())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.setCompany(company);

        return user;
    }

    @Override
    public UserDTO mapToDTO(User user, UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setCompany(user.getCompany().getId());
        return userDTO;
    }
}
