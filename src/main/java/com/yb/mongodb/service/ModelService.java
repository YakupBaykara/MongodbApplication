package com.yb.mongodb.service;

import com.yb.mongodb.mapper.ModelMapper;
import com.yb.mongodb.model.Model;
import com.yb.mongodb.model.dto.ModelDTO;
import com.yb.mongodb.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelService {
    private ModelRepository modelRepository;
    private ModelMapper modelMapper;

    public List<ModelDTO> findAll() {
        List<Model> models = modelRepository.findAll(Sort.by("id"));
        return models.stream()
                .map(model -> modelMapper.mapToDTO(model, new ModelDTO()))
                .sorted().toList();
//                .collect(Collectors.toList());
    }

    public ModelDTO get(String id) {
        Model model =modelRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        return modelMapper.mapToDTO(model, new ModelDTO());
    }

    public String create(ModelDTO modelDTO) {
        Model model = new Model();
        modelMapper.mapToEntity(modelDTO, model);
        model.setId(modelDTO.getId());
        return modelRepository.save(model).getId();
    }

    public void update(String id, ModelDTO modelDTO) {
        Model model = modelRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        Model updateModel = modelMapper.mapToEntity(modelDTO, model);
        modelRepository.save(updateModel);
    }

    public void delete(String id) {
        modelRepository.deleteById(id);
    }

    public List<ModelDTO> findAllModelsByProjectId(String projectId) {
        List<Model> models =  modelRepository.findByProjectId(projectId);
        return models.stream()
                .map(model -> modelMapper.mapToDTO(model, new ModelDTO()))
                .sorted()
                .toList();
    }
    public List<ModelDTO> findAllModelsByUserId(String userId) {
        List<Model> models = modelRepository.findByUserId(userId);
        return models.stream()
                .map(model -> modelMapper.mapToDTO(model, new ModelDTO()))
                .sorted()
                .toList();
    }

    public List<ModelDTO> findAllModelsByCompanyId(String companyId) {
        List<Model> models = modelRepository.findByCompanyId(companyId);
        return models.stream()
                .map(model -> modelMapper.mapToDTO(model, new ModelDTO()))
                .sorted()
                .toList();
    }
}
