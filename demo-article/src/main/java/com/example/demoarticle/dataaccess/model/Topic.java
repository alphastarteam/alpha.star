package com.example.demoarticle.dataaccess.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Topic {
    @Id
    @GeneratedValue
    private long id;
}
