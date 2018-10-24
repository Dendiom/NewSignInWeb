package com.lucky.NewSignInWeb.dao;

import com.lucky.NewSignInWeb.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User getUserByUsername(String username);
    User getUserById(long id);
}
