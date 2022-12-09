package com.yb.mongodb.controller;

import com.yb.mongodb.model.dto.CompanyDTO;
import com.yb.mongodb.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@AllArgsConstructor
public class CompanyController {

    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        return ResponseEntity.ok(companyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompany(@PathVariable String id) {
        return ResponseEntity.ok(companyService.get(id));
    }

    @PostMapping
    public  ResponseEntity<Void> save(@RequestBody CompanyDTO companyDTO) {
        companyService.create(companyDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCompany(@PathVariable String id, @RequestBody CompanyDTO companyDTO) {
        companyService.update(id, companyDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable String id) {
        companyService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
