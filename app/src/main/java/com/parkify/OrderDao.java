package com.parkify;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.parkify.Order;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Dao
public interface OrderDao {

    @Query("select * from `order`")
    List<Order> getAll();

    @Query("SELECT * FROM `order` where user LIKE  :userName")
    List<Order> findAllByName(String userName);

    @Query("SELECT COUNT(*) from `order`")
    int countOrders();

    @Query("SELECT * FROM `order` where user LIKE  :userName and beginTime >= :thisHour")
    List<Order> findNewByName(String userName, int thisHour);
/*
    List<Order> findNewByName(String userName, Date thisDate, int thisHour);
*/


    @Query("SELECT * FROM `order` where user LIKE  :userName and beginTime <= :thisHour")
    List<Order> findOldByName(String userName, int thisHour);

    /*
        List<Order> findOldByName(String userName, Date thisDate, int thisHour);
    */
    @Query("SELECT * FROM `order`  where number LIKE  :number")
    List<Order> findOrdersByPlace(int number);

    @Query("SELECT * FROM `order`  where number LIKE  :number and date LIKE :thisDate and ((beginTime <= :beginTimeQuery and :beginTimeQuery<=endTime) or (beginTime <= :endTimeQuery and :endTimeQuery<=endTime)) ")
    List<Order> findOrdersByPlaceAndTime(int number, String thisDate, int beginTimeQuery, int endTimeQuery);


    @Insert
    void insert(Order order);

    @Insert
    void insertAll(Order... orders);

    @Delete
    void delete(Order order);

    @Delete
    public void deleteAll(Order... orders);

    @Update
    public void updateUsers(Order... orders);
}