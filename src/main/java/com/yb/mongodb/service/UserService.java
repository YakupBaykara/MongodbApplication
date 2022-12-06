package com.yb.mongodb.service;

import com.yb.mongodb.model.Company;
import com.yb.mongodb.model.User;
import com.yb.mongodb.model.UserDTO;
import com.yb.mongodb.repository.CompanyRepository;
import com.yb.mongodb.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private CompanyRepository companyRepository;
    public List<UserDTO> findAll() {
        return userRepository.findAll(Sort.by("id"))
                .stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .collect(Collectors.toList());
    }

    public UserDTO get(String id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String create(UserDTO userDTO) {
        User user = new User();
        mapTOEntity(userDTO, user);
        user.setId(userDTO.getId());
        return userRepository.save(user).getId();
    }
    public void update(String id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapTOEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }

    // MAPPER ISLEMLERI
    private UserDTO mapToDTO(User user, UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setCompany(user.getCompany().getId());
        return userDTO;
    }

    private User mapTOEntity(UserDTO userDTO, User user) {
        user.setName(userDTO.getName());

        Company company = userDTO.getCompany() == null ? null : companyRepository.findById(userDTO.getCompany())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.setCompany(company);

        return user;
    }
}
