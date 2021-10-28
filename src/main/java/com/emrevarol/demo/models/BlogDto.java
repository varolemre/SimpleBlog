package com.emrevarol.demo.models;

import lombok.Data;

@Data
public class BlogDto {

    private String title;

    private String content;

    private String image;

    private Long authorId;

    private Long[] categoryIds;

    private String[] tags;
}
