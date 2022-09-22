package com.blackphoenixproductions.forumfrontend.dto.post;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class InsertPostDTO {
    @NotBlank(message = "Il messaggio del post non può essere null/vuoto")
    private String message;
    @NotEmpty(message = "Il messaggio del post non puo' essere null/vuoto")
    @Size(max = 20000, message = "Il messaggio del post ha superato il massimo di 20000 caratteri")
    private Long topicId;

    public InsertPostDTO(String message, Long topicId) {
        this.message = message;
        this.topicId = topicId;
    }
}
