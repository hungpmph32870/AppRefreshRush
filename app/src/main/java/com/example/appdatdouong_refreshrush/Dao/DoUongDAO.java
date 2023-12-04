package com.example.appdatdouong_refreshrush.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appdatdouong_refreshrush.DataBase.DbHelper;
import com.example.appdatdouong_refreshrush.Model.DoUong;


import java.util.ArrayList;
import java.util.List;

public class DoUongDAO {
    private final DbHelper dbHelper;
    public DoUongDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public boolean delete(int id) {
       SQLiteDatabase db = dbHelper.getWritableDatabase();
       long row = db.delete("DOUONG", "IDDOUONG =?", new String[]{String.valueOf(id)});
       return (row > 0);
    }
    public boolean insert(DoUong obj) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        values.put("TENDOUONG", obj.getTenDoUong());
        values.put("GIADOUONG", obj.getGiaDoUong());
        values.put("TINHTRANG", obj.getTinhTrang());
        values.put("DIACHI", obj.getDiaChiQuan());
        long row = db.insert("DOUONG", null, values);
        return (row > 0);
    }

    public boolean update(DoUong obj) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        values.put("TENDOUONG", obj.getTenDoUong());
        values.put("GIADOUONG", obj.getGiaDoUong());
        values.put("TINHTRANG", obj.getTinhTrang());
        values.put("DIACHI", obj.getDiaChiQuan());
        long row = db.update("DOUONG", values, "IDDOUONG = ?", new String[]{String.valueOf(obj.getIdDoUong())});
        return (row > 0);
    }

    @SuppressLint("Range")
    public ArrayList<DoUong> selectAll(){
        ArrayList<DoUong> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try{
            Cursor cursor = db.rawQuery("SELECT * FROM DOUONG", null);
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    DoUong obj = new DoUong();
                    obj.setIdDoUong(cursor.getInt(0));
                    obj.setTenDoUong(cursor.getString(1));
                    obj.setGiaDoUong(cursor.getInt(2));
                    obj.setTinhTrang(cursor.getString(3));
                    obj.setDiaChiQuan(cursor.getString(4));
                    list.add(obj);
                    cursor.moveToNext();
                }
            }
        }catch (Exception ex){

        }
        return list;

    }
    public List<DoUong> getSaleSanPhamList() {
        String sql = "SELECT * FROM DOUONG WHERE TINHTRANG = 'sale'";
        return selectAll();
    }
}
