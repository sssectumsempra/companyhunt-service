package com.companyHunt.app.repository.company;

import com.companyHunt.app.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByName(String name);

    void deleteByName(String name);
}
