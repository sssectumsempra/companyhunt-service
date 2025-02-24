package com.companyHunt.app.mapper.company;

import com.companyHunt.app.dto.CompanyCreateDto;
import com.companyHunt.app.dto.CompanyDto;
import com.companyHunt.app.dto.CompanyUpdateDto;
import com.companyHunt.app.mapper.jsonnullable.JsonNullableMapper;
import com.companyHunt.app.model.Company;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Consumer;

@Mapper(
        uses = JsonNullableMapper.class,
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class CompanyMapper {

    @Autowired
    private JsonNullableMapper jsonNullableMapper;

    public abstract Company map(CompanyCreateDto dto);

    public abstract CompanyDto map(Company company);

    public void update(CompanyUpdateDto dto, @MappingTarget Company company) {
        updateFields(dto.getName(), company::setName);
        updateFields(dto.getWebsite(), company::setWebsite);
        updateFields(dto.getDescription(), company::setDescription);
        updateFields(dto.getRating(), company::setRating);
    }

    private <T> void updateFields(JsonNullable<T> dto, Consumer<T> company) {
        if (jsonNullableMapper.isPresent(dto)) {
            company.accept(jsonNullableMapper.unwrap(dto));
        }
    }
}
