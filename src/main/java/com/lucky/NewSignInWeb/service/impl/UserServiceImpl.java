package com.lucky.NewSignInWeb.service.impl;

import com.lucky.NewSignInWeb.bean.Result;
import com.lucky.NewSignInWeb.bean.User;
import com.lucky.NewSignInWeb.dao.UserDao;
import com.lucky.NewSignInWeb.enums.Code;
import com.lucky.NewSignInWeb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Result register(String username, String password) {
        try {
            User user = userDao.getUserByUsername(username);
            if (user != null) {
                return new Result<>(Code.USER_HAS_ALREADY_REGISTERED, "用户已存在");
            }

            user = new User(username, password);
            user = userDao.save(user);
            return new Result<>(Code.SUCCESS, user.getId());
        } catch (Exception e) {
            return new Result<>(Code.MYSQL_ERROR, "mysql error");
        }
    }

    @Override
    public Result login(String username, String password) {
        try {
            User user = userDao.getUserByUsername(username);
            if (user == null) {
                return new Result<>(Code.USER_NOT_REGISTERED, "用户未注册");
            }

            if (password.equals(user.getPassword())) {
                return new Result<>(Code.SUCCESS, user);
            }

            return new Result<>(Code.PASSWORD_ERROR, "密码错误");
        } catch (Exception e) {
            return new Result<>(Code.MYSQL_ERROR, "mysql error");
        }
    }

    @Override
    public Result perfectInfo(User user) {
        User originUser = userDao.getUserById(user.getId());

        if (!StringUtils.isEmpty(user.getNickname())) {
            originUser.setNickname(user.getNickname());
        }
        if (!StringUtils.isEmpty(user.getSex())) {
            originUser.setSex(user.getSex());
        }
        if (!StringUtils.isEmpty(user.getGrade())) {
            originUser.setGrade(user.getGrade());
        }
        if (!StringUtils.isEmpty(user.getPhone())) {
            originUser.setPhone(user.getPhone());
        }
        if (!StringUtils.isEmpty(user.getMail())) {
            originUser.setMail(user.getMail());
        }
        if (!StringUtils.isEmpty(user.getDescription())) {
            originUser.setDescription(user.getDescription());
        }

        System.out.println(originUser);

        originUser.setGmt_modified(new Timestamp(System.currentTimeMillis()));
        try {
            userDao.save(originUser);
        } catch (Exception e) {
            return new Result<>(Code.MYSQL_ERROR, "mysql error");
        }
        return new Result<>(Code.SUCCESS, "");
    }

    @Override
    public Result getUserInfo(long id) {
        User user;
        try {
            user = userDao.getUserById(id);
        } catch (Exception e) {
            return new Result<>(Code.MYSQL_ERROR, "mysql error");
        }

        if (user != null) {
            return new Result<>(Code.SUCCESS, user);
        }

        return new Result<>(Code.USER_NOT_EXITS, "用户不存在");
    }
}
