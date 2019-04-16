package com.epam.kinorating.model.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    private final Integer id;

    public Entity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}