package com.lucky.NewSignInWeb.util;

import com.lucky.NewSignInWeb.constant.Constants;
import com.sun.istack.internal.NotNull;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    /**
     * 通过Cookie名称获取Cookie信息.
     * @param name   Cookie名.
     * @param servletRequest
     * @return String.
     */
    public static String getCookieValue(@NotNull String name, ServletRequest servletRequest) {
        String val = null;
        Cookie[] cookies = ((HttpServletRequest)servletRequest).getCookies();
        if (cookies == null) {
            return null;
        }

        for (Cookie cookie: cookies) {
            if (name.equals(cookie.getName())) {
                val = cookie.getValue();
                break;
            }
        }

        return val;
    }

    /**
     * 删除所有Cookie.
     * @param response
     */
    public static void deleteCookies(HttpServletResponse response) {
        for (String cookie: Constants.Cookies.ALL_COOKIE) {
            Cookie delCookie = new Cookie(cookie, null);
            delCookie.setMaxAge(0);
            delCookie.setPath("/");
            response.addCookie(delCookie);
        }
    }
}
