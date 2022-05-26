package com.blackphoenixproductions.forumfrontend.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ValidationUtility {

    private static final Logger logger = LoggerFactory.getLogger(ValidationUtility.class);

    public static boolean isValidTopicInput(String title, String message){
        if( (title != null && !title.isEmpty()) && ( message != null && !message.isEmpty() ) ){
            if(title.length() >= 5){
                return true;
            }
        }
        logger.error("Topic non valido.");
        return false;
    }

    public static boolean isValidPostInput(String message){
        if( message != null && !message.isEmpty() ){
            return true;
        }
        logger.error("Post non valido.");
        return false;
    }
}
