package com.sumansoul.roomdemo;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.sumansoul.roomdemo.bean.Book;
import com.sumansoul.roomdemo.bean.User;
import com.sumansoul.roomdemo.utils.BookDao;
import com.sumansoul.roomdemo.utils.UserDao;

/**
 * 数据库版本
 */
@Database(entities = {User.class, Book.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    private static final Object sLock = new Object();

    public static AppDatabase getInstance() {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(BaseAplication.getContext(), AppDatabase.class, "room.db")
                        //崩溃后重建
                        .fallbackToDestructiveMigration()
                        //允许主线程访问数据库
                        .allowMainThreadQueries()
                        //升级
                        .addMigrations(MIGRATION_1_2)
                        .build();
            }
            return INSTANCE;
        }
    }

    /**
     * UserDao
     * @return
     */
    public abstract UserDao userDao();
    /**
     * BookDao
     * @return
     */
    public abstract BookDao bookDao();

    /**
     * 数据库升级
     */
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // 为旧表添加新的字段
            database.execSQL("ALTER TABLE user ADD age INTEGER Default 0 not null ");
            //创建新的数据表
            database.execSQL("CREATE TABLE IF NOT EXISTS `book` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `address` TEXT)");
        }
    };
}