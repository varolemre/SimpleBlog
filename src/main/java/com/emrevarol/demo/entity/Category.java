package com.emrevarol.demo.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Category extends BaseEntity {

    private String name;

    private String description;

    @ManyToMany
    private Set<Blog> blogs;

}
