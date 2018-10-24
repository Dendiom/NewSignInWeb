package com.lucky.NewSignInWeb.service;

import com.lucky.NewSignInWeb.bean.Result;
import com.lucky.NewSignInWeb.bean.User;
import org.springframework.stereotype.Service;

public interface UserService {

    Result register(String username, String password);
    Result login(String username, String password);
    Result perfectInfo(User user);
    Result getUserInfo(long id);
}
