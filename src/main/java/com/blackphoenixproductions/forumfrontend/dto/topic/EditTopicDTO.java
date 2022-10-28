package com.blackphoenixproductions.forumfrontend.dto.topic;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EditTopicDTO {
    private Long id;
    @NotBlank(message = "Il messaggio del topic non puo' essere vuoto/null")
    private String message;
}
