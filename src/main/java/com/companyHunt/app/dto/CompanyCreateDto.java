package com.companyHunt.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyCreateDto {

    private String name;

    private String website;

    private String description;

    private Integer rating;
}
