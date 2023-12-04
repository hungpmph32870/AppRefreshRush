package com.example.appdatdouong_refreshrush.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "QLSP";

    public DbHelper(Context context){super(context, DB_NAME, null, 10);}

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
        sqLiteDatabase.execSQL("CREATE TABLE DOUONG (" +
                "IDDOUONG INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "TENDOUONG TEXT NOT NULL,"+
                "GIADOUONG INTEGER NOT NULL,"+
                "TINHTRANG TEXT NOT NULL,"+
                "DIACHI TEXT NOT NULL)");
        sqLiteDatabase.execSQL("INSERT INTO DOUONG VALUES(1, 'STING', 123000, 'Sale', 'Cầu Giấy - Hà Nội')," +
                " (2, 'BIA 333', 12000, 'Khong Sale', 'Cầu Giấy - Hà Nội')," +
                " (3, 'Nuoc Dua', 10000, 'Sale', 'Cầu Giấy - Hà Nội')," +
                " (4, 'Nuoc Tao', 10000, 'Sale', 'Cầu Giấy - Hà Nội')");
        sqLiteDatabase.execSQL("CREATE TABLE DOUONGNEW (" +
                "IDNEW INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "TENDOUONGNEW TEXT NOT NULL, "+
                "GIADOUONGNEW INTEGER NOT NULL, "+
                "DIACHINEW TEXT NOT NULL)");
        sqLiteDatabase.execSQL("INSERT INTO DOUONGNEW VALUES(1, 'COCA COLA', 13000, 'Mỹ Đình - Hà Nội')," +
                " (2, 'PEPSI', 15000, 'Hà Đông - Hà Nội')," +
                " (3, 'BÒ HÚC', 10000, 'Đình Thôn - Hà Nội')," +
                " (4, 'NƯỚC CAM', 20000, 'Mỹ Đình - Hà Nội')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i!=i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DOUONG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DOUONGNEW");
            onCreate(sqLiteDatabase);
        }
    }
}
