package com.emrevarol.demo.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createDate;

    private boolean active;

    @PrePersist
    public void onPrePersist() {
        this.setCreateDate(new Date());
        this.setActive(true);
    }

}
