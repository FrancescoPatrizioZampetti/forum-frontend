package com.blackphoenixproductions.forumfrontend.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditPostDTO {

    private Long id;
    private String message;

    public EditPostDTO(Long id) {
        this.id = id;
    }
}
