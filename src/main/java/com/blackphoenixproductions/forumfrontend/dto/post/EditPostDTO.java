package com.blackphoenixproductions.forumfrontend.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditPostDTO {
    private Long id;
    @NotEmpty(message = "Il messaggio del post non puo' essere null/vuoto")
    @Size(max = 20000, message = "Il messaggio del post ha superato il massimo di 20000 caratteri")
    private String message;

    public EditPostDTO(Long id) {
        this.id = id;
    }
}
