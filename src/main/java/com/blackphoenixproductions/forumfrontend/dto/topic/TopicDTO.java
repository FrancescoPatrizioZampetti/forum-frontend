package com.blackphoenixproductions.forumfrontend.dto.topic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicDTO {
    private Long id;
    private String title;
    private String message;
    private boolean pinned;
    private boolean emailUser;
    private LocalDateTime createDate;
    private LocalDateTime deleteDate;
    private LocalDateTime editDate;
}
