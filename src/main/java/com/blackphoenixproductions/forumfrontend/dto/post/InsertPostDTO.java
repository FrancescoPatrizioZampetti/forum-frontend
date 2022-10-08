package com.blackphoenixproductions.forumfrontend.dto.post;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class InsertPostDTO {
    @NotBlank(message = "Il messaggio del post non può essere null/vuoto")
    private String message;
    private Long topicId;

    public InsertPostDTO(String message, Long topicId) {
        this.message = message;
        this.topicId = topicId;
    }
}
