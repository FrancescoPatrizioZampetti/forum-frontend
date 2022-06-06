package com.blackphoenixproductions.forumfrontend.dto;


import com.blackphoenixproductions.forumfrontend.dto.topic.TopicDTO;
import com.blackphoenixproductions.forumfrontend.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO implements Comparable<NotificationDTO>{

    private Long id;
    private UserDTO fromUser;
    private UserDTO toUser;
    private TopicDTO topic;
    private String message;
    private LocalDateTime createDate;
    private String timeDifferenceFromNow;
    private String url;

    @Override
    public int compareTo(NotificationDTO o) {
        return o.createDate.compareTo(this.createDate);
    }
}
