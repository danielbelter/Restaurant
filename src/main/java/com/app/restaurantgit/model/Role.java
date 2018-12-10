package com.app.restaurantgit.model;

public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");
    private String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    }
