package com.companyHunt.app.controller;

import com.companyHunt.app.dto.CompanyCreateDto;
import com.companyHunt.app.dto.CompanyDto;
import com.companyHunt.app.dto.CompanyUpdateDto;
import com.companyHunt.app.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.ResolveNaturalIdEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "${application.endpoint.controller.root}")
@RequiredArgsConstructor
@Slf4j
public class CompanyHuntController {

    private final CompanyService service;

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getById(@PathVariable("id") Long id) {
        log.info("Received request to get company by id: {}", id);
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/by-name")
    public ResponseEntity<CompanyDto> getByName(@RequestParam("name") String name) {
        log.info("Received request to get company by name: {}", name);
        return ResponseEntity.ok(service.getByName(name));
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAll() {
        log.info("Received request to get company list");
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<CompanyDto> save(@RequestBody CompanyCreateDto dto) {
        log.info("Received request to save company.");
        service.save(dto);
        log.info("Received request to return saved company.");
        CompanyDto saved = service.getByName(dto.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> updateById(@PathVariable("id") Long id,
                                                 @RequestBody CompanyUpdateDto dto) {
        log.info("Received request to update company by id: {}", id);
        service.updateById(id, dto);
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/delete/by-name")
    public ResponseEntity<String> deleteByName(@RequestParam("name") String name) {
        log.info("Received request to delete company by name: {}", name);
        service.deleteByName(name);
        return ResponseEntity.ok("Company deleted successfully");
    }
}
