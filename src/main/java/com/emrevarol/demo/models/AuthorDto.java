package com.emrevarol.demo.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthorDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

}
