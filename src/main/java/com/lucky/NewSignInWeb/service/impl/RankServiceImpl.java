package com.lucky.NewSignInWeb.service.impl;

import com.lucky.NewSignInWeb.bean.Rank;
import com.lucky.NewSignInWeb.bean.Result;
import com.lucky.NewSignInWeb.dao.RankDao;
import com.lucky.NewSignInWeb.enums.Code;
import com.lucky.NewSignInWeb.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankDao rankDao;

    @Override
    public Result getRank(String week) {
        try {
            List<Map<String, Object>> maps = rankDao.getRank(week);
            List<Rank> ranks = new ArrayList<>();
            // 不做转换的话，前台显示会有问题
            for (Map map: maps) {
                Rank rank = new Rank();
                rank.setCount(((BigDecimal)map.get("count")).longValue());
                rank.setGrade((String)map.get("grade"));
                rank.setNickname((String)map.get("nickname"));
                ranks.add(rank);
            }
            return new Result<>(Code.SUCCESS, ranks);
        } catch (SQLException e) {
            e.printStackTrace();
            e.printStackTrace();
            return new Result<>(Code.MYSQL_ERROR, "mysql error");
        }
    }
}
