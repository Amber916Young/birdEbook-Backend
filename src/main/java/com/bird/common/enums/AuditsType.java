package com.bird.common.enums;


public enum AuditsType {
    REVIEWED("reviewed"),
    NOTREVIEWED("notReviewed"),
    FAIL("fail");

    private String label;

    AuditsType(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
