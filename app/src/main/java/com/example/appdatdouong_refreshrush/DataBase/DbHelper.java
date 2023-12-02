package com.example.appdatdouong_refreshrush.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "QLSP";

    public DbHelper(Context context){super(context, DB_NAME, null, 4);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE NGUOIDUNG\n" +
                "(\n" +
                "\t IDNGUOIDUNG TEXT PRIMARY KEY, \n" +
                "\t HOTEN TEXT NOT NULL ,\n" +
                "\t TENTAIKHOAN TEXT NOT NULL,\n" +
                "\t EMAIL TEXT NOT NULL,\n" +
                "\t SODIENTHOAI TEXT NOT NULL,\n" +
                "\t MATKHAU TEXT NOT NULL\n " +
                "\t)\n"+
                "\t");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i!=i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            onCreate(sqLiteDatabase);
        }
    }
}
