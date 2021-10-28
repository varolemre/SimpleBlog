package com.emrevarol.demo.entity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
public class Blog extends BaseEntity{

    private String title;

    private String content;

    private String image;

    @ElementCollection
    private Set<String> tags;

    @ManyToMany
    private Set<Category> category;

    @OneToOne
    private Author author;

}
