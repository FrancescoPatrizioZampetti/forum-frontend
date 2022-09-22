package com.blackphoenixproductions.forumfrontend.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditPostDTO {
    private Long id;
    @NotBlank(message = "Il messaggio del post non può essere null/vuoto")
    private String message;

    public EditPostDTO(Long id) {
        this.id = id;
    }
}
