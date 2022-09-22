package com.blackphoenixproductions.forumfrontend.dto.post;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class InsertPostDTO {
    @NotBlank(message = "Il messaggio del post non può essere null/vuoto")
    private String message;
    @NotNull(message = "L'id del topic non puo' essere null")
    private Long topicId;

    public InsertPostDTO(String message, Long topicId) {
        this.message = message;
        this.topicId = topicId;
    }
}
