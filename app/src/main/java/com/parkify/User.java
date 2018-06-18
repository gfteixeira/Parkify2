package com.parkify;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;


import java.util.Date;
import java.util.UUID;

@Entity(tableName = "user")
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