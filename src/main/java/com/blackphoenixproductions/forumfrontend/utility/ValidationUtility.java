package com.blackphoenixproductions.forumfrontend.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ValidationUtility {

    private static final Logger logger = LoggerFactory.getLogger(ValidationUtility.class);

    public static boolean isValidMessage(String message) {
        if (message != null && !message.isEmpty()) {
            return true;
        }
        logger.error("Messaggio non valido.");
        return false;
    }

    public static boolean isValidTitle(String title) {
        if (title != null && !title.isEmpty() && title.length() >= 5) {
            return true;
        }
        logger.error("Titolo non valido.");
        return false;
    }


}
