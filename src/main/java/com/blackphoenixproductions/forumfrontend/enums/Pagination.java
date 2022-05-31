package com.blackphoenixproductions.forumfrontend.enums;

public enum Pagination {

    TOPIC_PAGINATION (3),
    POST_PAGINATION (10);

    private final int value;

    Pagination(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
