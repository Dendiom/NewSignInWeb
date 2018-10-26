package com.lucky.NewSignInWeb.dao;

import com.lucky.NewSignInWeb.bean.Rank;
import com.lucky.NewSignInWeb.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public interface RankDao extends JpaRepository<User, Long>{

    @Query(value = "select sum(count) as count, user.nickname, user.grade from record,user " +
            " where record.week = ? and user.username = record.username " +
            "group by record.username order by count desc;", nativeQuery = true)
    List<Map<String, Object>> getRank(String week) throws SQLException;
}
