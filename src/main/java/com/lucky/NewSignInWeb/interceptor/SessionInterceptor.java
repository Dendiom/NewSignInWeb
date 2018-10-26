package com.lucky.NewSignInWeb.interceptor;

import com.lucky.NewSignInWeb.bean.Result;
import com.lucky.NewSignInWeb.constant.Constants;
import com.lucky.NewSignInWeb.enums.Code;
import com.lucky.NewSignInWeb.service.UserService;
import com.lucky.NewSignInWeb.util.Base64Util;
import com.lucky.NewSignInWeb.util.CookieUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    private Logger logger = Logger.getLogger(HandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse httpServletResponse, Object o) throws Exception {

        HttpSession session = request.getSession();
        if (session.getAttribute(Constants.SessionAttrs.USER) == null) {
            //refresh
            String uid = CookieUtil.getCookieValue(Constants.Cookies.UID, request);
            if (uid == null) {
                httpServletResponse.sendRedirect( request.getServletContext().getContextPath() + "/login");
                return false;
            }

            long id = Long.valueOf(Base64Util.decode(CookieUtil.getCookieValue(Constants.Cookies.UID, request)));
            Result result = userService.getUserInfo(id);
            if (result.getCode() == Code.SUCCESS) {
                logger.info("refresh session info userId = " + id);
                session.setAttribute(Constants.SessionAttrs.USER, result.getObj());
            } else {
                logger.error(result.getObj());
            }
        }
        return true;
    }
}
