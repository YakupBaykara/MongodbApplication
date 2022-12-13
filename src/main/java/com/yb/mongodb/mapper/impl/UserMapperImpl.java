package com.yb.mongodb.mapper.impl;

import com.yb.mongodb.mapper.UserMapper;
import com.yb.mongodb.model.Company;
import com.yb.mongodb.model.Project;
import com.yb.mongodb.model.User;
import com.yb.mongodb.model.dto.UserDTO;
import com.yb.mongodb.repository.CompanyRepository;
import com.yb.mongodb.repository.ProjectRepository;
import com.yb.mongodb.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapperImpl implements UserMapper {

    private CompanyRepository companyRepository;
    private ProjectRepository projectRepository;
    private ProjectService projectService;
    @Override
    public User mapToEntity(UserDTO userDTO, User user) {
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        user.setPassword(userDTO.getPassword());

        // SET COMPANY
        Company company = userDTO.getCompany() == null
                ? null : companyRepository.findById(userDTO.getCompany())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.setCompany(company);

        // SET PROJECTS
        Set<Project> projects = iterableToSet(projectRepository.findAllById(
                userDTO.getProjects() == null ? Collections.emptySet() : userDTO.getProjects()));
        if(projects.size() != (userDTO.getProjects() == null ? 0 : userDTO.getProjects().size()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "some of projects not found");
        user.getProjects().addAll(projects);

        return user;
    }

    @Override
    public UserDTO mapToDTO(User user, UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setAddress(user.getAddress());
        userDTO.setPassword(user.getPassword());
        userDTO.setCompany(user.getCompany().getId());
        userDTO.setProjects(user.getProjects()
                .stream().map(Project::getId)
                .collect(Collectors.toSet()));
        return userDTO;
    }

    private <T> Set<T> iterableToSet(Iterable<T> iterable) {
        Set<T> set = new HashSet<>();
        iterable.forEach(item -> set.add(item));
        return set;
    }
}
