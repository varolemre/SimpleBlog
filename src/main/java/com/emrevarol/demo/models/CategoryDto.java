package com.emrevarol.demo.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

}
