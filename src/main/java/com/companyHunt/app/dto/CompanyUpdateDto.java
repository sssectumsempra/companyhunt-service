package com.companyHunt.app.dto;

import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Setter
@Getter
public class CompanyUpdateDto {


    private JsonNullable<String> name;

    private JsonNullable<String> website;

    private JsonNullable<String> description;

    private JsonNullable<Integer> rating;
}
