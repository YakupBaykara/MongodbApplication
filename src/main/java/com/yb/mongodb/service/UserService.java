package com.yb.mongodb.service;

import com.yb.mongodb.mapper.UserMapper;
import com.yb.mongodb.model.Project;
import com.yb.mongodb.model.User;
import com.yb.mongodb.model.dto.UserDTO;
import com.yb.mongodb.repository.ProjectRepository;
import com.yb.mongodb.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private ProjectRepository projectRepository;
    private ProjectService projectService;
    private UserMapper userMapper;
    public List<UserDTO> findAll() {
        return userRepository.findAll(Sort.by("id"))
                .stream()
                .map(user -> userMapper.mapToDTO(user, new UserDTO()))
                .collect(Collectors.toList());
    }

    public UserDTO get(String id) {
        return userRepository.findById(id)
                .map(user -> userMapper.mapToDTO(user, new UserDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String create(UserDTO userDTO) {
        User user = new User();
        userMapper.mapToEntity(userDTO, user);
        userRepository.save(user);
        for (Project project : user.getProjects()) {
            projectService.addUser(project.getId(), user.getId());
        }
        return user.getId();
    }

    public void update(String id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userMapper.mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public List<UserDTO> getByCompanyId(String companyId) {
        List<User> users = userRepository.findByCompanyId(companyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<UserDTO> userDTOs = users.stream()
                .map(user -> userMapper.mapToDTO(user, new UserDTO()))
                .collect(Collectors.toList());
        return userDTOs;
    }

    public Integer getCompanyUsersCount(String companyId) {
        return userRepository.countByCompanyId(companyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<UserDTO> getByProjectId(String projectId) {
        List<User> users = userRepository.findByProjectsId(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<UserDTO> userDTOs = users.stream()
                .map(user -> userMapper.mapToDTO(user, new UserDTO()))
                .collect(Collectors.toList());
        return userDTOs;
    }

    public Integer getProjectUsersCount(String projectId) {
        return userRepository.countByProjectsId(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }
}
