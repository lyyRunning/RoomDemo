package com.sumansoul.roomdemo.utils;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sumansoul.roomdemo.bean.Book;
import com.sumansoul.roomdemo.bean.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("select * from user where first_name = (:name)")
    List<User> getUser(String name);

    @Insert
    void insertAll(User... users);
    @Update
    void update(User user);
    @Delete
    void delete(User user);
}
