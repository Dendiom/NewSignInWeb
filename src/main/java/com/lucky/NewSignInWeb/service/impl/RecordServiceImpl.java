package com.lucky.NewSignInWeb.service.impl;

import com.lucky.NewSignInWeb.bean.Record;
import com.lucky.NewSignInWeb.bean.Result;
import com.lucky.NewSignInWeb.dao.RecordDao;
import com.lucky.NewSignInWeb.enums.Code;
import com.lucky.NewSignInWeb.service.RecordService;
import com.lucky.NewSignInWeb.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordDao recordDao;


    @Override
    public Result getWeekRecords(String username, String week) {
        try {
            List<Record> records = recordDao.getRecordsByUsernameAndWeek(username, week);
            return new Result<>(Code.SUCCESS, records);
        } catch (Exception e) {
            return new Result<>(Code.MYSQL_ERROR, "mysql error");
        }
    }

    @Override
    public Result signInFirstTime(String username, Date time) {
        try {
            Record record = new Record();
            // 为了适配数据库设计与hibernate工作方式
            record.setUsername(username);
            record.setWeek(TimeUtil.getWeekIdentifier());
            record.setDay_of_week(TimeUtil.getDayOfWeek());
            switch (TimeUtil.getDayPeriod()) {
                case 0:
                    record.setIn_time_mor(new Timestamp(time.getTime()));
                    break;
                case 1:
                    record.setIn_time_noon(new Timestamp(time.getTime()));
                    break;
                case 2:
                    record.setIn_time_eve(new Timestamp(time.getTime()));
                    break;
            }

            record = recordDao.save(record);
            return new Result<>(Code.SUCCESS, record.getId());
        } catch (Exception e) {
            return new Result<>(Code.MYSQL_ERROR, "mysql error");
        }
    }

    @Override
    public Result signInUpdate(long id, Date time) {
        try {
            Record record = recordDao.getRecordById(BigInteger.valueOf(id));
            int count = record.getCount() + 1800;
            record.setCount(count);
            record.setGmt_modified(new Timestamp(time.getTime()));
            switch (TimeUtil.getDayPeriod()) {
                case 0:
                    record.setIn_time_mor(new Timestamp(time.getTime()));
                    break;
                case 1:
                    record.setIn_time_noon(new Timestamp(time.getTime()));
                    break;
                case 2:
                    record.setIn_time_eve(new Timestamp(time.getTime()));
                    break;
            }
            recordDao.save(record);
            return new Result<>(Code.SUCCESS, "");
        } catch (Exception e) {
            return new Result<>(Code.MYSQL_ERROR, "mysql error");
        }
    }

    @Override
    public Result signOut(long id, Date time) {
        try {
            Record record = recordDao.getRecordById(BigInteger.valueOf(id));
            int count = record.getCount();
            long inTime = 0;
            int periodOfDay = TimeUtil.getDayPeriod();

            // 获取签到的时间
            switch (periodOfDay) {
                case 0:
                    inTime = record.getIn_time_mor().getTime() / 1000;
                    record.setOut_time_mor(new Timestamp(time.getTime()));
                    break;
                case 1:
                    inTime = record.getIn_time_noon().getTime() / 1000;
                    record.setOut_time_noon(new Timestamp(time.getTime()));
                    break;
                case 2:
                    inTime = record.getIn_time_eve().getTime() / 1000;
                    record.setOut_time_eve(new Timestamp(time.getTime()));
                    break;
                default:
                    break;
            }

            int add = (int)(time.getTime() / 1000 - inTime);
            if (add > 1800) {
                count = count + add - 1800;
            }

            // 更新
            record.setCount(count);
            record.setGmt_modified(new Timestamp(time.getTime()));
            recordDao.save(record);
            return new Result<>(Code.SUCCESS, "");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(Code.MYSQL_ERROR, "mysql error");
        }
    }
}
