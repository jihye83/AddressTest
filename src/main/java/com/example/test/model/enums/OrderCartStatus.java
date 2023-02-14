package com.example.test.model.enums;

public enum OrderCartStatus {
    OPEN("open"),
    INPROCESS("inprocess"),
    CLOSED("closed");

    private String description;

    OrderCartStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
