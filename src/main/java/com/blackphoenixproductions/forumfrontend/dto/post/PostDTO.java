package com.blackphoenixproductions.forumfrontend.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long id;
    private String message;
    private LocalDateTime createDate;
    private LocalDateTime deleteDate;
    private LocalDateTime editDate;

}
