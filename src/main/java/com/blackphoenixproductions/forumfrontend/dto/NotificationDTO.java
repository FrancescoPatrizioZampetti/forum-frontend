package com.blackphoenixproductions.forumfrontend.dto;


import com.blackphoenixproductions.forumfrontend.utility.DateUtility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO implements Comparable<NotificationDTO> {

    private String id;
    private String fromUser;
    private String fromUserRole;
    private String toUser;
    private String topicTitle;
    private String message;
    private String url;
    private LocalDateTime createDate;
    private String timeDifferenceFromNow;


    public String getTimeDifferenceFromNow() {
        return DateUtility.setTimeDifferenceFromNow(createDate);
    }

    @Override
    public int compareTo(NotificationDTO o) {
        return o.createDate.compareTo(this.createDate);
    }
}
