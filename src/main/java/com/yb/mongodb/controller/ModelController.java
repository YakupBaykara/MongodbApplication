package com.yb.mongodb.controller;

import com.yb.mongodb.model.Model;
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
    public ResponseEntity<List<Model>> getAllModels() {
        return ResponseEntity.ok(modelService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Model> getModel(@PathVariable String id) {
        return ResponseEntity.ok(modelService.get(id));
    }

    @PostMapping
    public ResponseEntity<Void> createModel(@RequestBody Model model) {
        modelService.create(model);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateModel(@PathVariable String id, @RequestBody Model model) {
        modelService.update(id, model);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable String id) {
        modelService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Model>> getAllModelsByProjectId(@PathVariable String projectId) {
        return ResponseEntity.ok(modelService.findAllModelsByProjectId(projectId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Model>> getAllModelsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(modelService.findAllModelsByUserId(userId));
    }
}
