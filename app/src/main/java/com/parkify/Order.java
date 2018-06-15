package com.parkify;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.sql.Time;
import java.util.Date;

@Entity(tableName = "order")
public class Order {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    private String id;

    @ColumnInfo(name = "number")
    @NonNull
    private String number;

    @ColumnInfo(name = "date")
    @NonNull
    @TypeConverters(DateConverter.class)
    private Date date;

    @ColumnInfo(name = "user")
    @NonNull
    private String userName;

    @ColumnInfo(name = "beginTime")
    @NonNull
    private Time beginTime;

    @ColumnInfo(name = "endTime")
    @NonNull
    private Time endTime;

    @ColumnInfo(name = "duration")
    @NonNull
    private String duration;

    public Order(String userName, String number, Date date, Time beginTime, Time endTime, String duration ) {
        this.userName = userName;
        this.number = number;
        this.date = date;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.id = number+"_"+beginTime.toString();
    }

    @Ignore
    public Order() {
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getBeginTime() { return beginTime; }

    public void setBeginTime(Time beginTime) {
        this.beginTime = beginTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }



}
