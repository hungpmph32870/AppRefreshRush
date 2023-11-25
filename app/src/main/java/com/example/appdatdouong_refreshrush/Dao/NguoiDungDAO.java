package com.example.appdatdouong_refreshrush.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appdatdouong_refreshrush.DataBase.DbHelper;
import com.example.appdatdouong_refreshrush.Model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO {
    private SQLiteDatabase db;
    private String pass;
    private String user;


    public NguoiDungDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    public long updatePass(NguoiDung obj){
        ContentValues values = new ContentValues();
        values.put("HOTEN", obj.getHoTen());
        values.put("MATKHAU", obj.getMatKhau());
        return db.update("NGUOIDUNG", values, "TENTAIKHOAN = ?", new String[]{String.valueOf(obj.getTenTaiKhoan())});

    }
    public List<NguoiDung> getAll(){
        String sql = "SELECT * FROM NGUOIDUNG";
        return getData(sql);
    }
    public NguoiDung getID(String id) {
        String sql = "SELECT * FROM NGUOIDUNG WHERE HOTEN=?";
        List<NguoiDung> list = getData(sql, id);
        return list.get(0);
    }

    public int checklogin(String tenTaiKhoan, String matKhau){
        String sql = "SELECT * FROM NGUOIDUNG WHERE TENTAIKHOAN = ? AND MATKHAU = ?";
        List<NguoiDung> list = getData(sql, tenTaiKhoan, matKhau);
        if (list.size() == 0){
            return - 1;
        }
        return 1;
    }
    public long insert(NguoiDung nd){
        ContentValues values = new ContentValues();
        values.put("HOTEN", nd.getHoTen());
        values.put("TENTAIKHOAN", nd.getTenTaiKhoan());
        values.put("EMAIL", nd.getEmail());
        values.put("SODIENTHOAI", nd.getSoDienThoai());
        values.put("MATKHAU", nd.getMatKhau());
        return db.insert("NGUOIDUNG", null, values);

    }
    @SuppressLint("Range")
    private List<NguoiDung> getData(String sql, String... selectionArgs){
        List<NguoiDung> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while(cursor.moveToNext()){
            NguoiDung obj = new NguoiDung();
            obj.setHoTen(cursor.getString(cursor.getColumnIndex("HOTEN")));
            obj.setTenTaiKhoan(cursor.getString(cursor.getColumnIndex("TENTAIKHOAN")));
            obj.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
            obj.setSoDienThoai(cursor.getString(cursor.getColumnIndex("SODIENTHOAI")));
            obj.setMatKhau(cursor.getString(cursor.getColumnIndex("MATKHAU")));
            list.add(obj);
        }
        return list;
    }
}
