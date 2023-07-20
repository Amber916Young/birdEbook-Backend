package com.bird.common.enums;/*
 */

public enum LikeStatus {
    UNLIKED(0), // 未点赞
    LIKED(1);   // 已点赞

    private int label;
    LikeStatus(int value) {
        this.label = value;
    }

    public int getValue() {
        return label;
    }

    public static LikeStatus fromValue(int value) {
        for (LikeStatus status : LikeStatus.values()) {
            if (status.label == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid LikeStatus value: " + value);
    }
}

