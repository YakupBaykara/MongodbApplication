package com.yb.mongodb.service;

import com.yb.mongodb.model.Model;
import com.yb.mongodb.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelService {
    private ModelRepository modelRepository;

    public List<Model> findAll() {
        return modelRepository.findAll(Sort.by("id"));
    }

    public Model get(String id) {
        return modelRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String create(Model model) {
        return modelRepository.save(model).getId();
    }

    public void update(String id, Model model) {
        Model updateModel = modelRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        modelRepository.save(updateModel);
    }

    public void delete(String id) {
        modelRepository.deleteById(id);
    }

    public List<Model> findAllModelsByProjectId(String projectId) {
        return modelRepository.findByProjectId(projectId);
    }
    public List<Model> findAllModelsByUserId(String userId) {
        return modelRepository.findByUserId(userId);
    }
}
