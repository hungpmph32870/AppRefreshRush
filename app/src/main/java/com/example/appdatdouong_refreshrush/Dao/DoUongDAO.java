package com.example.appdatdouong_refreshrush.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.appdatdouong_refreshrush.DataBase.DbHelper;
import com.example.appdatdouong_refreshrush.Model.DoUong;


import java.util.ArrayList;
import java.util.List;

public class DoUongDAO {
    private SQLiteDatabase db;
    public DoUongDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long delete(String id) {
        return db.delete("DOUONG", "IDDOUONG = ?", new String[]{String.valueOf(id)});
    }
    public long insert(DoUong obj) {
        ContentValues values = new ContentValues();
        values.put("IDDOUONG", obj.getIdDoUong());
        values.put("TENDOUONG", obj.getTenDoUong());
        values.put("GIADOUONG", obj.getGiaDoUong());
        values.put("TINHTRANG", obj.getTinhTrang());
        values.put("DIACHI", obj.getDiaChiQuan());
        return db.insert("DOUONG", null, values);
    }

    public long update(DoUong obj) {
        ContentValues values = new ContentValues();
        values.put("IDDOUONG", obj.getIdDoUong());
        values.put("TENDOUONG", obj.getTenDoUong());
        values.put("GIADOUONG", obj.getGiaDoUong());
        values.put("TINHTRANG", obj.getTinhTrang());
        values.put("DIACHI", obj.getDiaChiQuan());
        return db.update("DOUONG", values, "IDDOUONG = ?", new String[]{String.valueOf(obj.getIdDoUong())});
    }

    public List<DoUong> getAll() {
        String sql = "SELECT * FROM DOUONG";
        return getData(sql);
    }

    public DoUong getID(String id) {
        String sql = "SELECT * FROM DOUONG WHERE IDDOUONG=?";
        List<DoUong> list = getData(sql, id);
        return list.get(0);
    }
    @SuppressLint("Range")
    public List<DoUong> LayDS(){
        List<DoUong> listDS = new ArrayList<DoUong>();
        String query = "SELECT * FROM DOUONG" ;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            DoUong obj = new DoUong();
            obj.setIdDoUong(cursor.getInt(cursor.getColumnIndex("IDDOUONG")));
            obj.setTenDoUong(cursor.getString(cursor.getColumnIndex("TENDOUONG")));
            obj.setGiaDoUong(cursor.getInt(cursor.getColumnIndex("GIADOUONG")));
            obj.setTinhTrang(cursor.getString(cursor.getColumnIndex("TINHTRANG")));
            obj.setDiaChiQuan(cursor.getString(cursor.getColumnIndex("DIACHI")));
            listDS.add(obj);

            cursor.moveToNext();
        }
        return listDS;
    }

    @SuppressLint("Range")
    private List<DoUong> getData(String sql, String... selectionArgs) {
        List<DoUong> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            DoUong obj = new DoUong();
            obj.setIdDoUong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("IDDOUONG"))));
            obj.setTenDoUong(cursor.getString(cursor.getColumnIndex("TENDOUONG")));
            obj.setGiaDoUong(cursor.getInt(cursor.getColumnIndex("GIADOUONG")));
            obj.setTinhTrang(cursor.getString(cursor.getColumnIndex("TINHTRANG")));
            obj.setDiaChiQuan(cursor.getString(cursor.getColumnIndex("DIACHI")));
            Log.i("//==", obj.toString());
            list.add(obj);
        }
        return list;
    }
}
