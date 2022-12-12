package com.yb.mongodb.service;

import com.yb.mongodb.model.Company;
import com.yb.mongodb.model.Project;
import com.yb.mongodb.model.dto.ProjectDTO;
import com.yb.mongodb.model.User;
import com.yb.mongodb.repository.CompanyRepository;
import com.yb.mongodb.repository.ProjectRepository;
import com.yb.mongodb.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectService {

    private ProjectRepository projectRepository;
    private CompanyRepository companyRepository;
    private UserRepository userRepository;
    public List<ProjectDTO> findAll() {
        return projectRepository.findAll(Sort.by("id"))
                .stream()
                .map(project -> mapToDTO(project, new ProjectDTO()))
                .collect(Collectors.toList());
    }

    public ProjectDTO get(String id) {
        return projectRepository.findById(id)
                .map(project -> mapToDTO(project, new ProjectDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String create(ProjectDTO projectDTO) {
        Project project =  new Project();
        mapToEntity(projectDTO, project);
        project.setId(projectDTO.getId());
        return projectRepository.save(project).getId();
    }

    public void update(String id, ProjectDTO projectDTO) {
        Project project = projectRepository.findById(projectDTO.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(projectDTO, project);
        projectRepository.save(project);
    }

    public void addUsers(String id, Set<String> userIdList) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Set<User> users = iterableToSet(userRepository.findAllById(userIdList));
        project.getUsers().addAll(users);
        projectRepository.save(project);
    }
    public void removeUsers(String id, Set<String> userIdList) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Set<User> users = iterableToSet(userRepository.findAllById(userIdList));
        project.getUsers().removeAll(users);
        projectRepository.save(project);
    }

    public void delete(String id) {
        projectRepository.deleteById(id);
    }

    public List<ProjectDTO> findAllByCompanyIdAndUsersId(String companyId, String userId) {
        List<Project> projects =  projectRepository.findAllByCompanyIdAndUsersId(companyId, userId);
        List<ProjectDTO> projectDTOs = projects.stream()
                .map(project -> mapToDTO(project, new ProjectDTO()))
                .collect(Collectors.toList());
        return projectDTOs;
    }

    // MAPPER ISLEMLERI
    private ProjectDTO mapToDTO(Project project, ProjectDTO projectDTO) {
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setCompany(project.getCompany().getId());

        projectDTO.setUsers(project.getUsers() == null ? null : project.getUsers()
                .stream()
                .map(user -> user.getId())
                .collect(Collectors.toSet()));
        return projectDTO;
        }

        private Project mapToEntity(ProjectDTO projectDTO, Project project) {
            project.setName(projectDTO.getName());

            // Set Company
            Company company = projectDTO.getCompany() == null
                    ? null : companyRepository.findById(projectDTO.getCompany())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            project.setCompany(company);

            // Set Users
            Set<User> users = iterableToSet(userRepository.findAllById(
                    projectDTO.getUsers() == null ? Collections.emptySet() : projectDTO.getUsers()));
            if(users.size() != (projectDTO.getUsers() == null ? 0 : projectDTO.getUsers().size()))
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "some of users not found");
            project.getUsers().addAll(users.stream().collect(Collectors.toSet()));

            return project;
        }
        // List icin de uygulanabilir.
        private <T> Set<T> iterableToSet(Iterable<T> iterable) {
            Set<T> set = new HashSet<>();
            iterable.forEach(item -> set.add(item));
            return set;
        }
}
