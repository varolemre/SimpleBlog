package com.emrevarol.demo.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class BlogUpdateDto {

    @NotBlank
    private String title;

    @NotBlank
    @Size(min = 10, max = 10000)
    private String content;

    @NotBlank
    private String image;

    private Long[] categoryIds;

    private String[] tags;
}
