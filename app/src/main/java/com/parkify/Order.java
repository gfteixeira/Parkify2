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

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    public int id;

    @ColumnInfo(name = "number")
    @NonNull
    private int number;

    @ColumnInfo(name = "date")
    @NonNull
    @TypeConverters(DateConverter.class)
    private Date date;

    @ColumnInfo(name = "user")
    @NonNull
    private String userName;

    @ColumnInfo(name = "beginTime")
    @NonNull
    private int beginTime;

    @ColumnInfo(name = "endTime")
    @NonNull
    private int endTime;

    @ColumnInfo(name = "duration")
    private String duration;

    public Order(String userName, int number, Date date, int beginTime, int endTime, String duration ) {
        this.userName = userName;
        this.number = number;
        this.date = date;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    @Ignore
    public Order() {
    }

    public void setID(int id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getBeginTime() { return beginTime; }

    public void setBeginTime(int beginTime) {
        this.beginTime = beginTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }



    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }



}
