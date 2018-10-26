package com.lucky.NewSignInWeb.bean;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "record")
public class Record {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private BigInteger id;
    private String week;
    private String username;
    private Integer day_of_week;
    private Timestamp in_time_mor;
    private Timestamp out_time_mor;
    private Timestamp in_time_noon;
    private Timestamp out_time_noon;
    private Timestamp in_time_eve;
    private Timestamp out_time_eve;
    private Integer count;
    private Timestamp gmt_create;
    private Timestamp gmt_modified;

    public Record() {
        this.count = 1800;
        this.gmt_create = new Timestamp(System.currentTimeMillis());
        this.gmt_modified = new Timestamp(System.currentTimeMillis());
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(Integer day_of_week) {
        this.day_of_week = day_of_week;
    }

    public Timestamp getIn_time_mor() {
        return in_time_mor;
    }

    public void setIn_time_mor(Timestamp in_time_mor) {
        this.in_time_mor = in_time_mor;
    }

    public Timestamp getOut_time_mor() {
        return out_time_mor;
    }

    public void setOut_time_mor(Timestamp out_time_mor) {
        this.out_time_mor = out_time_mor;
    }

    public Timestamp getIn_time_noon() {
        return in_time_noon;
    }

    public void setIn_time_noon(Timestamp in_time_noon) {
        this.in_time_noon = in_time_noon;
    }

    public Timestamp getOut_time_noon() {
        return out_time_noon;
    }

    public void setOut_time_noon(Timestamp out_time_noon) {
        this.out_time_noon = out_time_noon;
    }

    public Timestamp getIn_time_eve() {
        return in_time_eve;
    }

    public void setIn_time_eve(Timestamp in_time_eve) {
        this.in_time_eve = in_time_eve;
    }

    public Timestamp getOut_time_eve() {
        return out_time_eve;
    }

    public void setOut_time_eve(Timestamp out_time_eve) {
        this.out_time_eve = out_time_eve;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Timestamp getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Timestamp gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Timestamp getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modified(Timestamp gmt_modified) {
        this.gmt_modified = gmt_modified;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", week='" + week + '\'' +
                ", username='" + username + '\'' +
                ", day_of_week=" + day_of_week +
                ", in_time_mor=" + in_time_mor +
                ", out_time_mor=" + out_time_mor +
                ", in_time_noon=" + in_time_noon +
                ", out_time_noon=" + out_time_noon +
                ", in_time_eve=" + in_time_eve +
                ", out_time_eve=" + out_time_eve +
                ", count=" + count +
                ", gmt_create=" + gmt_create +
                ", gmt_modified=" + gmt_modified +
                '}';
    }
}
