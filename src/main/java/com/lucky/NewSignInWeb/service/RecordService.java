package com.lucky.NewSignInWeb.service;

import com.lucky.NewSignInWeb.bean.Result;

import java.util.Date;

public interface RecordService {

    Result getWeekRecords(String username, String week);
    Result signInFirstTime(String username, Date time);
    Result signInUpdate(long id, Date time);
    Result signOut(long id, Date time);
}
