package com.yb.mongodb.mapper.impl;

import com.yb.mongodb.mapper.ModelMapper;
import com.yb.mongodb.model.Company;
import com.yb.mongodb.model.Model;
import com.yb.mongodb.model.Project;
import com.yb.mongodb.model.User;
import com.yb.mongodb.model.dto.ModelDTO;
import com.yb.mongodb.repository.CompanyRepository;
import com.yb.mongodb.repository.ProjectRepository;
import com.yb.mongodb.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@AllArgsConstructor
public class ModelMapperImpl implements ModelMapper {
    private CompanyRepository companyRepository;
    private UserRepository userRepository;
    private ProjectRepository projectRepository;

    @Override
    public Model mapToEntity(ModelDTO modelDTO, Model model) {
        model.setName(modelDTO.getName());
        model.setDescription(modelDTO.getDescription());

        // SET COMPANY
        Company company = modelDTO.getCompany() == null
                ? null : companyRepository.findById(modelDTO.getCompany())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.setCompany(company);

        // SET USER
        User user = modelDTO.getUser() == null
                ? null : userRepository.findById(modelDTO.getUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.setUser(user);

        // SET PROJECT
        Project project = modelDTO.getProject() == null
                ? null : projectRepository.findById(modelDTO.getProject())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.setProject(project);

        return model;
    }

    @Override
    public ModelDTO mapToDTO(Model model, ModelDTO modelDTO) {
        modelDTO.setId(model.getId());
        modelDTO.setName(model.getName());
        modelDTO.setDescription(model.getDescription());
        modelDTO.setCompany(model.getCompany().getId());
        modelDTO.setUser(model.getUser().getId());
        modelDTO.setProject(model.getProject().getId());
        return modelDTO;
    }
}
