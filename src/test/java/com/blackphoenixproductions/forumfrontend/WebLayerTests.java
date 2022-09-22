package com.blackphoenixproductions.forumfrontend;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Disabled
public class WebLayerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_forum() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("forum"));
    }

    @Test
    public void test_viewtopic() throws Exception {
        this.mockMvc.perform(get("/viewtopic")
                        .param("id", "1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("forum-single"));
    }


    @Test
    public void test_createTopic() throws Exception {
        this.mockMvc.perform(post("/createTopic")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("title", "ciao mondo")
                        .param("message", "sono un messaggio")
                        .param("pinned", "true")
                        .param("emailUser", "true")
                )
                .andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("forum-single"));
    }

}
