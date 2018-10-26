package com.lucky.NewSignInWeb.dao;

import com.lucky.NewSignInWeb.bean.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface RecordDao extends JpaRepository<Record, Long> {

    Record getRecordById(BigInteger id);
    List<Record> getRecordsByUsernameAndWeek(String username, String week);

}
