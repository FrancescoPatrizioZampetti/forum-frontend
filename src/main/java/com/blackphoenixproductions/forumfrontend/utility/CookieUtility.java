package com.blackphoenixproductions.forumfrontend.utility;

import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtility {

    public static final String ACCESS_TOKEN_NAME = "access_token";
    public static final String REFRESH_TOKEN_NAME = "refresh_token";

    public static void setCookie(String token, HttpServletResponse httpResponse, boolean isSecureCookie, String parameterName){
        Cookie cookie = new Cookie(parameterName, token);
        setCommonCookieAttributes(isSecureCookie, cookie, 2700000);
        httpResponse.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletResponse httpResponse, boolean isSecureCookie, String parameterName){
        Cookie cookie = new Cookie(parameterName, null);
        setCommonCookieAttributes(isSecureCookie, cookie, 0);
        httpResponse.addCookie(cookie);
    }

    private static void setCommonCookieAttributes(boolean isSecureCookie, Cookie cookie, int maxAge) {
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        cookie.setSecure(isSecureCookie);
        cookie.setHttpOnly(true);
    }

    public static String getTokenFromCookie(HttpServletRequest httpServletRequest, String parameterName){
        String jwtToken = null;
        Cookie cookie = WebUtils.getCookie(httpServletRequest, parameterName);
        if (cookie != null){
            jwtToken = cookie.getValue();
        }
        return jwtToken;
    }


}
