package com.bird.common.enums;


public enum OperationType {
    INSERT("Insert"),
    DELETE("Delete"),
    UPDATE("Update"),
    EXPORT("Export"),
    SELECT("Select"),
    LOGON("Login"),
    OTHER("Other");
    private String label;

    OperationType(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
