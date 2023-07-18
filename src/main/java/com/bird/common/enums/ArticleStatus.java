package com.bird.common.enums;

/**
 * @author birdyyoung
 * @ClassName:WikiStatus
 * @Description:
 * @Date: 06/07/2023 20:21
 * @Version: v1.0
 */
public enum ArticleStatus {
    ACTIVE("Active"),
    DEACTIVATE("Deactivate");

    private String label;

    ArticleStatus(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
