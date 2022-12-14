package com.yb.mongodb.service;

import com.yb.mongodb.mapper.ProjectMapper;
import com.yb.mongodb.model.Project;
import com.yb.mongodb.model.dto.ProjectDTO;
import com.yb.mongodb.model.User;
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
    private ProjectMapper projectMapper;
    private UserRepository userRepository;
    public List<ProjectDTO> findAll() {
        return projectRepository.findAll(Sort.by("id"))
                .stream()
                .map(project -> projectMapper.mapToDTO(project, new ProjectDTO()))
                .collect(Collectors.toList());
    }

    public ProjectDTO get(String id) {
        return projectRepository.findById(id)
                .map(project -> projectMapper.mapToDTO(project, new ProjectDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String create(ProjectDTO projectDTO) {
        Project project =  new Project();
        projectMapper.mapToEntity(projectDTO, project);
        project.setId(projectDTO.getId());
        return projectRepository.save(project).getId();
    }

    public void update(String id, ProjectDTO projectDTO) {
        Project project = projectRepository.findById(projectDTO.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        projectMapper.mapToEntity(projectDTO, project);
        projectRepository.save(project);
    }

    public void addUser(String id, String userId) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        project.getUsers().add(user);
        projectRepository.save(project);
    }
 /*   public void addUsers(String id, Set<String> userIdList) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Set<User> users = userRepository.findAllById(userIdList)
                .stream().collect(Collectors.toSet());
        project.getUsers().addAll(users);
        projectRepository.save(project);
    } */
     public void removeUser(String id, String userId) {
         Project project = projectRepository.findById(id)
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
         User user = userRepository.findById(userId)
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
         project.getUsers().remove(user);
         projectRepository.save(project);
     }
/*    public void removeUsers(String id, Set<String> userIdList) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Set<User> users = userRepository.findAllById(userIdList)
                .stream().collect(Collectors.toSet());
        project.getUsers().removeAll(users);
        projectRepository.save(project);
    } */

    public void delete(String id) {
        projectRepository.deleteById(id);
    }

    public List<ProjectDTO> findAllByCompanyId(String companyId) {
        List<Project> projects =  projectRepository.findAllByCompanyId(companyId);
        List<ProjectDTO> projectDTOs = projects.stream()
                .map(project -> projectMapper.mapToDTO(project, new ProjectDTO()))
                .collect(Collectors.toList());
        return projectDTOs;
    }

    public List<ProjectDTO> findAllByUserId(String userId) {
        List<Project> projects =  projectRepository.findAllByUsersId(userId);
        List<ProjectDTO> projectDTOs = projects.stream()
                .map(project -> projectMapper.mapToDTO(project, new ProjectDTO()))
                .collect(Collectors.toList());
        return projectDTOs;
    }

    public List<ProjectDTO> findAllByCompanyIdAndUsersId(String companyId, String userId) {
        List<Project> projects =  projectRepository.findAllByCompanyIdAndUsersId(companyId, userId);
        List<ProjectDTO> projectDTOs = projects.stream()
                .map(project -> projectMapper.mapToDTO(project, new ProjectDTO()))
                .collect(Collectors.toList());
        return projectDTOs;
    }
}
