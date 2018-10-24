package com.lucky.NewSignInWeb.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class LogInterceptor implements HandlerInterceptor {
    private static Logger logger = Logger.getLogger(LogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object o) throws Exception {
        Map<String, Object> info = new HashMap<>();
        info.put("Request URI: ", httpServletRequest.getRequestURI());
        info.put("Remote Addr: ", httpServletRequest.getRemoteAddr());
        info.put("Query: ", httpServletRequest.getQueryString());
        Enumeration<String> params = httpServletRequest.getParameterNames();
        while (params.hasMoreElements()) {
            String name = params.nextElement();
            info.put("Param-" + name + ": ", httpServletRequest.getParameter(name));

        }
        logger.info(info);
        return true;
    }
}
