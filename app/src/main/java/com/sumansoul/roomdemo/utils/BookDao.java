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
public interface BookDao {
    @Query("SELECT * FROM book")
    List<Book> getAll();

    @Insert
    void insertAll(Book... books);

    @Delete
    void delete(Book book);

    @Update
    void update(Book book);
}
