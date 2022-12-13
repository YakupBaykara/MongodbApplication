package com.yb.mongodb.mapper.impl;

import com.yb.mongodb.mapper.ProjectMapper;
import com.yb.mongodb.model.Company;
import com.yb.mongodb.model.Project;
import com.yb.mongodb.model.User;
import com.yb.mongodb.model.dto.ProjectDTO;
import com.yb.mongodb.repository.CompanyRepository;
import com.yb.mongodb.repository.UserRepository;
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
public class ProjectMapperImpl implements ProjectMapper {

    private CompanyRepository companyRepository;
    private UserRepository userRepository;
    @Override
    public Project mapToEntity(ProjectDTO projectDTO, Project project) {
        project.setName(projectDTO.getName());

        // SET COMPANY
        Company company = projectDTO.getCompany() == null
                ? null : companyRepository.findById(projectDTO.getCompany())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        project.setCompany(company);

        // SET USERS
        Set<User> users = iterableToSet(userRepository.findAllById(
                projectDTO.getUsers() == null ? Collections.emptySet() : projectDTO.getUsers()));
        if(users.size() != (projectDTO.getUsers() == null ? 0 : projectDTO.getUsers().size()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "some of users not found");
        project.getUsers().addAll(users);

        return project;
    }

    @Override
    public ProjectDTO mapToDTO(Project project, ProjectDTO projectDTO) {
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setCompany(project.getCompany().getId());

        projectDTO.setUsers(project.getUsers() == null ? null : project.getUsers()
                .stream()
                .map(user -> user.getId())
                .collect(Collectors.toSet()));
        return projectDTO;
    }

    private <T> Set<T> iterableToSet(Iterable<T> iterable) {
        Set<T> set = new HashSet<>();
        iterable.forEach(item -> set.add(item));
        return set;
    }
}
