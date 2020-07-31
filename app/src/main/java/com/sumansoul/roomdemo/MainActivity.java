package com.sumansoul.roomdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sumansoul.roomdemo.bean.Book;
import com.sumansoul.roomdemo.bean.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        //创建User对象
        user = new User(2, "小明", "北京朝阳区", 22);
        /**
         * 增加
         */
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 3; i < 6; i++) {
                    //添加User用户
                    AppDatabase.getInstance().userDao().insertAll(new User(i, "小明", "北京朝阳区", 18));
                }
                //添加Book
                AppDatabase.getInstance().bookDao().insertAll(new Book(1, "中华故事会", "上海市长宁区"));
            }
        });

        /**
         * 删
         */
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //删除User用户
                AppDatabase.getInstance().userDao().delete(user);
            }
        });
        /**
         * 改
         */
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //更改User用户
                user.setLastName("哈哈哈哈");
                user.setFirstName("涛哥");
                AppDatabase.getInstance().userDao().update(user);
            }
        });

        /**
         * 查
         */
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //查询User用户
                List<User> users = AppDatabase.getInstance().userDao().getUser("小明");
                Log.d("LUO", "users=====" + users.size());
                //查询Book
                List<Book> bookList = AppDatabase.getInstance().bookDao().getAll();
                Log.d("LUO", "bookList=====" + bookList.size());
            }
        });
    }
}