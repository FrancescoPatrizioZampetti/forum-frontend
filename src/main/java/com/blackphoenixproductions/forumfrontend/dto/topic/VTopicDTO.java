package com.blackphoenixproductions.forumfrontend.dto.topic;

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
public class VTopicDTO {

    private Long id;
    private String title;
    private String message;
    private boolean pinned;
    private boolean emailUser;
    private LocalDateTime createDate;
    private LocalDateTime deleteDate;
    private LocalDateTime editDate;
    private String authorUsername;
    private String authorEmail;
    private String timeDifferenceFromNow;
    private Long postsNumber;

    public String getTimeDifferenceFromNow() {
        return DateUtility.setTimeDifferenceFromNow(createDate);
    }

}
