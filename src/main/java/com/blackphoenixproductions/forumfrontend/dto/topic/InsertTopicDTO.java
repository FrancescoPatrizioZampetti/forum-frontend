package com.blackphoenixproductions.forumfrontend.dto.topic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertTopicDTO {
    private String title;
    private String message;
    private boolean pinned;
    private boolean emailUser;
}
