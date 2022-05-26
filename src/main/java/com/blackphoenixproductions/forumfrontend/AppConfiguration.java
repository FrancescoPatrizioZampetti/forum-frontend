package com.blackphoenixproductions.forumfrontend;

import com.blackphoenixproductions.forumfrontend.utility.ValueUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class AppConfiguration {

    private final ValueUtility valueUtility;

    @Autowired
    public AppConfiguration(ValueUtility valueUtility) {
        this.valueUtility = valueUtility;
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public WebClient webClient(){
        return WebClient.create(valueUtility.getBackendPath());
    }


}
