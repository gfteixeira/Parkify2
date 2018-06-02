package com.parkify;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.parkify.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user where user_name LIKE  :userName")
    User findByName(String userName);

    @Query("SELECT COUNT(*) from user")
    int countUsers();

    @Insert
    void insert(User user);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Delete
    public void deleteAll(User... users);

    @Update
    public void updateUsers(User... users);
}