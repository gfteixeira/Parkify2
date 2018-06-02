package com.parkify;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;


import java.util.Date;
import java.util.UUID;

/*
@Entity(tableName = "users")
public class com.parkify.User {

    @PrimaryKey
    @ColumnInfo(name = "username")
    private String uUserName;

    @ColumnInfo(name = "password")
    private String uPassword;

    @ColumnInfo(name = "last_update")
    @TypeConverters(DateConverter.class)
    private Date uDate;


    public com.parkify.User(){

    }
    @Ignore
    public com.parkify.User(String userName, @NonNull String password) {
        this.uPassword = password;
        this.uUserName = userName;
        uDate = new Date(System.currentTimeMillis());    }

    public String getUserName() {
        return uUserName;
    }

    public void setUserName(String userName) {
        this.uUserName = userName;
    }

    public String getPasswrod() {
        return uPassword;
    }

    public void setPasswrod(String passwrod) {
        this.uPassword = passwrod;
    }

*/

@Entity (tableName = "user")
public class User {


    @PrimaryKey
    @ColumnInfo(name = "user_name")
    @NonNull
    private String userName;

    @NonNull
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Ignore
    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwrod) {
        this.password = password;
    }
}