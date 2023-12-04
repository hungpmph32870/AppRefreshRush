package com.example.appdatdouong_refreshrush.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.appdatdouong_refreshrush.DataBase.DbHelper;
import com.example.appdatdouong_refreshrush.Model.DoUong;
import com.example.appdatdouong_refreshrush.Model.DoUongNew;

import java.util.ArrayList;
import java.util.List;

public class DoUongNewDAO {
    private SQLiteDatabase db;

    public DoUongNewDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long delete(String id) {
        return db.delete("DOUONGNEW", "IDNEW = ?", new String[]{String.valueOf(id)});
    }
    public long insert(DoUongNew obj) {
        ContentValues values = new ContentValues();
        values.put("IDNEW", obj.getIdNew());
        values.put("TENDOUONGNEW", obj.getTenNew());
        values.put("GIADOUONGNEW", obj.getGiaNew());
        values.put("DIACHINEW", obj.getDiaChiNew());
        return db.insert("DOUONGNEW", null, values);
    }

    public long update(DoUongNew obj) {
        ContentValues values = new ContentValues();
        values.put("IDNEW", obj.getIdNew());
        values.put("TENDOUONGNEW", obj.getTenNew());
        values.put("GIADOUONGNEW", obj.getGiaNew());
        values.put("DIACHINEW", obj.getDiaChiNew());
        return db.update("DOUONGNEW", values, "IDNEW = ?", new String[]{String.valueOf(obj.getIdNew())});
    }

    public List<DoUongNew> getAll() {
        String sql = "SELECT * FROM DOUONGNEW";
        return getData(sql);
    }

    public DoUongNew getID(String id) {
        String sql = "SELECT * FROM DOUONGNEW WHERE IDNEW=?";
        List<DoUongNew> list = getData(sql, id);
        return list.get(0);
    }


    @SuppressLint("Range")
    private List<DoUongNew> getData(String sql, String... selectionArgs) {
        List<DoUongNew> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            DoUongNew obj = new DoUongNew();
            obj.setIdNew(Integer.parseInt(cursor.getString(cursor.getColumnIndex("IDNEW"))));
            obj.setTenNew(cursor.getString(cursor.getColumnIndex("TENDOUONGNEW")));
            obj.setGiaNew(cursor.getInt(cursor.getColumnIndex("GIADOUONGNEW")));
            obj.setDiaChiNew(cursor.getString(cursor.getColumnIndex("DIACHINEW")));
            Log.i("//==", obj.toString());
            list.add(obj);
        }
        return list;
    }
}
