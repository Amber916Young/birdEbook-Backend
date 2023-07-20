package com.bird.common.enums;/*
 */

public enum LikeStatus {
    UNLIKED(0), // 未点赞
    LIKED(1);   // 已点赞

    private final int value;

    LikeStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static LikeStatus fromValue(int value) {
        for (LikeStatus status : LikeStatus.values()) {
            if (status.value == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid LikeStatus value: " + value);
    }
}

