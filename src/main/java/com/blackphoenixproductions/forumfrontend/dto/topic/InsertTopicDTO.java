package com.blackphoenixproductions.forumfrontend.dto.topic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsertTopicDTO {
    @NotEmpty(message = "Il titolo del topic non puo' essere null/vuoto")
    @Size(min = 5, max = 50, message = "Il titolo del topic puo' avere un minimo di 5 caratteri e un massimo di 50")
    private String title;
    @NotEmpty(message = "Il messaggio del topic non puo' essere null/vuoto")
    @Size(max = 20000, message = "Il messaggio del topic ha superato il massimo di 20000 caratteri")
    private String message;
    private boolean pinned;
    private boolean emailUser;
}
