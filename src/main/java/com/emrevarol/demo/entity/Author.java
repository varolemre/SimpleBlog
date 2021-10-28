package com.emrevarol.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.PrePersist;

@Entity
@Data
public class Author extends BaseEntity {

    private String firstName;

    private String lastName;

    private Long blogAmount;

    @PrePersist
    public void onPrePersist() {
        this.blogAmount=0L;
    }

}
