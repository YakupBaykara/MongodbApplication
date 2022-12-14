package com.yb.mongodb.controller;

import com.yb.mongodb.model.dto.UserDTO;
import com.yb.mongodb.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDTO userDTO) {
        userService.create(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        userService.update(id, userDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<UserDTO>> getUsersByCompanyId(@PathVariable String companyId) {
        return ResponseEntity.ok(userService.getByCompanyId(companyId));
    }
    @GetMapping("/count/company/{companyId}")
    public ResponseEntity<Integer> getUsersCountByCompanyId(@PathVariable String companyId) {
        return ResponseEntity.ok(userService.getCompanyUsersCount(companyId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<UserDTO>> getUsersByProjectId(@PathVariable String projectId) {
        return ResponseEntity.ok(userService.getByProjectId(projectId));
    }

    @GetMapping("/count/project/{projectId}")
    public ResponseEntity<Integer> getUsersCountByProjectId(@PathVariable String projectId) {
        return ResponseEntity.ok(userService.getProjectUsersCount(projectId));
    }
}
