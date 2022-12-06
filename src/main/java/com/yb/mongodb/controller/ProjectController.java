package com.yb.mongodb.controller;

import com.yb.mongodb.model.ProjectDTO;
import com.yb.mongodb.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectController {

    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProject(@PathVariable String id) {
        return ResponseEntity.ok(projectService.get(id));
    }

    @PostMapping
    public ResponseEntity<Void> createProject(@RequestBody ProjectDTO projectDTO) {
        projectService.create(projectDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProject(@PathVariable String id, @RequestBody ProjectDTO projectDTO) {
        projectService.update(id, projectDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable String id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/company/{id}/user/{userId}")
    public ResponseEntity<List<ProjectDTO>> getProjectByCompanyIdAndUserId(
            @PathVariable String id, @PathVariable String userId) {
        return ResponseEntity.ok(projectService.findAllByCompanyIdAndUsersId(id, userId));
    }
}
