package com.blackphoenixproductions.forumfrontend.dto.topic;

import com.blackphoenixproductions.forumfrontend.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private UserDTO user;
    private String formattedCreateDate;
    private String formattedEditDate;

    public String getFormattedCreateDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'Inviato il' dd/MM/yy 'alle' HH:mm");
        return formatter.format(createDate);
    }

    public String getFormattedEditDate() {
        String formattedDate = null;
        if(editDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'Ultima modifica il' dd/MM/yy 'alle' HH:mm");
            formattedDate = formatter.format(editDate);
        }
        return formattedDate;
    }


}
