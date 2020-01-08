package com.ysccc.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

/***
 *数据库第三方库：LitePal来操作数据库
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LitePal.getDatabase();//创建数据库
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***
                 * 添加数据
                 */
         Book book=new Book();
         book.setName("世界和平");
         book.setAuthor("威尼斯");
         book.setPages(300);
         book.setPrice(26.96);
         book.setPress("Unknow");
         book.save();
            }
        });
        findViewById(R.id.upDataButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***
                 * 更新数据：
                  */
               Book book=new Book();
                /* book.setName("你好世界");
                book.setAuthor("安妮");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();
                book.setPrice(10.99);
                book.save();*/
               book.setPrice(14.95);
               book.setPress("新华出版社");
               book.updateAll("name = ? and author = ?","你好世界","安妮");
            }
        });
        findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 删除数据
                 */
                DataSupport.deleteAll(Book.class,"price < ?","15");

            }
        });
        findViewById(R.id.queryButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 查询数据
                 */

                List<Book> books=DataSupport.findAll(Book.class); //所有数据
                for (Book book:books){
                    Log.i(TAG, "book name is "+book.getName());
                    Log.i(TAG, "book author is "+book.getAuthor());
                    Log.i(TAG, "book pages is "+book.getPages());
                    Log.i(TAG, "book price is "+book.getPrice());
                    Log.i(TAG, "book press is "+book.getPress());
                }
                Book firstBook=DataSupport.findFirst(Book.class);//查询第一条数据
                Book lastBook=DataSupport.findLast(Book.class);//查询最后一条数据
                List<Book> books1=DataSupport.select("name","author").find(Book.class);
                //select方法用于指定查询哪几列的数据
                List<Book> books2=DataSupport.where("page = ?","400").find(Book.class);
                //where用于指定查询的约束条件
                List<Book> books3=DataSupport.order("price desc").find(Book.class);
                //order指定结果的排序方式，desc表示降序，asc或者不写表示升序
                List<Book> books4=DataSupport.limit(3).find(Book.class);
                //limit指定查询结果的数量
                List<Book> books5=DataSupport.limit(3).offset(1).find(Book.class);
                //查询表中第2、3、4条数据；offset查询结果偏移量

            }
        });
    }

}
