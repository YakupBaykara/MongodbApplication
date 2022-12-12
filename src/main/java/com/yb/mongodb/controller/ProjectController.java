package com.yb.mongodb.controller;

import com.yb.mongodb.model.dto.ProjectDTO;
import com.yb.mongodb.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    @PatchMapping("/{id}/adduser")
    public ResponseEntity<Void> addProjectUsers(@PathVariable String id, @RequestBody Set<String> userIdList) {
        projectService.addUsers(id, userIdList);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{id}/removeuser")
    public ResponseEntity<Void> removeProjectUsers(@PathVariable String id, @RequestBody Set<String> userIdList) {
        projectService.removeUsers(id, userIdList);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable String id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/company/{companyId}/user/{userId}")
    public ResponseEntity<List<ProjectDTO>> getProjectByCompanyIdAndUserId(
            @PathVariable String companyId, @PathVariable String userId) {
        return ResponseEntity.ok(projectService.findAllByCompanyIdAndUsersId(companyId, userId));
    }

}
