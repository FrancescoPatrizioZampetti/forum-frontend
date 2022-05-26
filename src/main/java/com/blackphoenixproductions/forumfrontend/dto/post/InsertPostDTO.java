package com.blackphoenixproductions.forumfrontend.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertPostDTO {
    private String message;
    private Long topicId;

    public InsertPostDTO(String message, Long topicId) {
        this.message = message;
        this.topicId = topicId;
    }
}
