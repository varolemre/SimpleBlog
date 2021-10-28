package com.emrevarol.demo.entity;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Author extends BaseEntity {

    private String firstName;

    private String lastName;

    private Long blogAmount;

}
