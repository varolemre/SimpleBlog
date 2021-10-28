package com.emrevarol.demo.entity;

import javax.persistence.Entity;

@Entity
public class Author extends BaseEntity {

    private String firstName;

    private String lastName;

    private Long blogAmount;

}
