package tech.hoangphi.demo.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "weather.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table weathers (dia_diem text primary key, kieu_thoi_tiet text, nhiet_do integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists weathers");
        onCreate(db);
    }

    public void insertWeather(Weather weather) throws Exception{
        ContentValues values = new ContentValues();
        values.put("dia_diem", weather.getDiadiem());
        values.put("kieu_thoi_tiet", weather.getKieuThoiTiet());
        values.put("nhiet_do", weather.getNhietdo());

        SQLiteDatabase database = this.getWritableDatabase();
        try{
            database.insert("weathers", null, values);
        }catch (SQLException e){
            throw e;
        }
        database.close();
    }
}
