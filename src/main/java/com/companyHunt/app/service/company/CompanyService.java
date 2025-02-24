package com.companyHunt.app.service.company;

import com.companyHunt.app.dto.CompanyCreateDto;
import com.companyHunt.app.dto.CompanyDto;
import com.companyHunt.app.dto.CompanyUpdateDto;

import java.util.List;

public interface CompanyService {

    CompanyDto getById(Long id);

    CompanyDto getByName(String name);

    List<CompanyDto> getAll();

    void save(CompanyCreateDto dto);

    void deleteByName(String name);

    void updateById(Long id, CompanyUpdateDto dto);
}
