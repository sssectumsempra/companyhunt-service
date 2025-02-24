package com.companyHunt.app.service.company.impl;

import com.companyHunt.app.dto.CompanyCreateDto;
import com.companyHunt.app.dto.CompanyDto;
import com.companyHunt.app.dto.CompanyUpdateDto;
import com.companyHunt.app.exception.CompanyNotFoundException;
import com.companyHunt.app.mapper.company.CompanyMapper;
import com.companyHunt.app.model.Company;
import com.companyHunt.app.repository.company.CompanyRepository;
import com.companyHunt.app.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repository;

    private final CompanyMapper mapper;

    @Override
    public CompanyDto getById(Long id) {
        Optional<Company> company = repository.findById(id);
        if (company.isEmpty()) {
            log.warn("No companies found for id: {}", id);
            throw new CompanyNotFoundException("Company not found for id: " + id);
        }
        return mapper.map(company.get());
    }

    @Override
    public CompanyDto getByName(String name) {
        Company company = repository.findByName(name);
        if (company == null) {
            log.warn("No company found.");
        }
        return mapper.map(company);
    }

    @Override
    public List<CompanyDto> getAll() {
        List<CompanyDto> companies = repository.findAll().stream()
                .map(mapper::map)
                .toList();
        if (companies.isEmpty()) {
            log.warn("No companies found.");
        }
        return companies;
    }

    @Override
    public void save(CompanyCreateDto dto) {
        Company company = mapper.map(dto);
        repository.save(company);

        Long id = company.getId();

        Optional<Company> saved = repository.findById(id);
        if (saved.isEmpty()) {
            log.warn("No companies found for id: {}", id);
            throw new CompanyNotFoundException("Company not found for id: " + id);
        }
    }

    @Override
    public void deleteByName(String name) {
        log.warn("Received request to delete for name: {}", name);
        repository.deleteByName(name);
    }

    @Override
    public void updateById(Long id, CompanyUpdateDto dto) {
        log.warn("Received request to update for id: {}", id);
        Optional<Company> company = repository.findById(id);
        mapper.update(dto, company.get());
        repository.save(company.get());
    }
}
