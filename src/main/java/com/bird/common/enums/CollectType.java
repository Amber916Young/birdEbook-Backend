package com.bird.common.enums;

public enum CollectType {
    PRIVATE("Private"),
    PUBLIC("Public");

    private String label;

    CollectType(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
