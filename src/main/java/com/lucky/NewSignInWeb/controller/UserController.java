package com.lucky.NewSignInWeb.controller;

import com.lucky.NewSignInWeb.bean.Result;
import com.lucky.NewSignInWeb.bean.User;
import com.lucky.NewSignInWeb.constant.Constants;
import com.lucky.NewSignInWeb.enums.Code;
import com.lucky.NewSignInWeb.service.UserService;
import com.lucky.NewSignInWeb.util.Base64Util;
import com.lucky.NewSignInWeb.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller()
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login.do")
    public ModelAndView login(HttpServletRequest req, HttpServletResponse resp,
                              @RequestParam(name = "username") String username, @RequestParam("password") String password,
                              @RequestParam(name = "rememberMe", required = false) String rememberMe) {

        Result result = userService.login(username, password);

        // 成功登陆
        if (result.getCode() == Code.SUCCESS) {
            User user = (User) result.getObj();
            Cookie cookie = new Cookie(Constants.Cookies.UID, Base64Util.encode(String.valueOf(user.getId())));
            cookie.setMaxAge(rememberMe == null ? -1 : 365 * 24 * 60 * 60);
            cookie.setPath("/");
            resp.addCookie(cookie);

            HttpSession session = req.getSession();
            session.setAttribute(Constants.SessionAttrs.USER, user);

            return new ModelAndView("redirect:main/form");
        }

        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject(Constants.ReqAttrs.ERROR, result);
        return modelAndView;
    }

    @PostMapping("/register.do")
    public ModelAndView register(HttpServletRequest req, @RequestParam("username") String username,
                                 @RequestParam("password") String password) {

        Result result = userService.register(username, password);
        HttpSession session = req.getSession();
        if (result.getCode() == Code.SUCCESS) {   // 成功注册
            session.setAttribute(Constants.SessionAttrs.UID, result.getObj());
            return new ModelAndView("redirect:perfect");
        }

        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject(Constants.ReqAttrs.ERROR, result);
        return modelAndView;
    }

    @GetMapping("/logout.do")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        CookieUtil.deleteCookies(response);
        return new ModelAndView("redirect:login");
    }

    @PostMapping("/perfect.do")
    public ModelAndView perfect(HttpServletRequest req, @RequestParam("nickname") String nickname,
                                @RequestParam("sex") int sex, @RequestParam("grade") String grade,
                                @RequestParam("phone") String phone, @RequestParam("mail") String mail,
                                @RequestParam("description") String description) {

        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute(Constants.SessionAttrs.UID);
        User userBean = new User();
        ModelAndView modelAndView = new ModelAndView("perfect");
        if (userId == null) {
            modelAndView.addObject(Constants.ReqAttrs.ERROR, new Result<>(Code.SERVER_ERROR, "服务器错误"));
            return modelAndView;
        }

        userBean.setId(userId);
        userBean.setNickname(nickname);
        userBean.setSex(sex);
        userBean.setGrade(grade);
        userBean.setPhone(phone);
        userBean.setMail(mail);
        userBean.setDescription(description);
        Result result = userService.perfectInfo(userBean);

        if (Code.SUCCESS == result.getCode()) {
            return new ModelAndView("redirect:login");
        }

        modelAndView.addObject(Constants.ReqAttrs.ERROR, result);
        return modelAndView;
    }

    /**
     * test
     */
    @GetMapping("/test")
    public String test() {
//        User user = userService.getUserInfo(1);
//        System.out.println(user);
        return "login";
    }
}
