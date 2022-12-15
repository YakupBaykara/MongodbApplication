package com.yb.mongodb.controller;

import com.yb.mongodb.model.Model;
import com.yb.mongodb.model.dto.ModelDTO;
import com.yb.mongodb.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/models")
@AllArgsConstructor
public class ModelController {

    private ModelService modelService;

    @GetMapping
    public ResponseEntity<List<ModelDTO>> getAllModels() {
        return ResponseEntity.ok(modelService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelDTO> getModel(@PathVariable String id) {
        return ResponseEntity.ok(modelService.get(id));
    }

    @PostMapping
    public ResponseEntity<Void> createModel(@RequestBody ModelDTO modelDTO) {
        modelService.create(modelDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateModel(@PathVariable String id, @RequestBody ModelDTO modelDTO) {
        modelService.update(id, modelDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable String id) {
        modelService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ModelDTO>> getAllModelsByProjectId(@PathVariable String projectId) {
        return ResponseEntity.ok(modelService.findAllModelsByProjectId(projectId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ModelDTO>> getAllModelsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(modelService.findAllModelsByUserId(userId));
    }
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<ModelDTO>> getAllModelsByCompanyId(@PathVariable String companyId) {
        return ResponseEntity.ok(modelService.findAllModelsByCompanyId(companyId));
    }
}
