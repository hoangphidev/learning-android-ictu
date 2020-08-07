package tech.hoangphi.phieunhanxet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "qlnx.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table phieu(id integer primary key autoincrement, ten text, chucvu text, nhanxet text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists phieu");
        onCreate(db);
    }

    public ArrayList<Phieu> mangPhieu() {
        ArrayList<Phieu> mangPhieu = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs = db.rawQuery("select * from phieu", null);
        while (cs.moveToNext()) {
            Phieu phieu = new Phieu(
                    cs.getString(1),
                    cs.getString(2),
                    cs.getString(3)
            );
            mangPhieu.add(phieu);
        }
        return mangPhieu;
    }

    public Phieu layPhieu() {
        ArrayList<Phieu> mangPhieu = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs = db.rawQuery("select * from phieu order by id desc", null);
        while (cs.moveToNext()) {
            Phieu phieu = new Phieu(
                    cs.getString(1),
                    cs.getString(2),
                    cs.getString(3)
            );
            mangPhieu.add(phieu);
        }
        Phieu phieu = mangPhieu.get(0);
        return phieu;
    }

    public void themPhieu(Phieu phieu) {
        ContentValues values = new ContentValues();
        values.put("ten", phieu.getTen());
        values.put("chucvu", phieu.getChucVu());
        values.put("nhanxet", phieu.getNhanXet());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("phieu", null, values);
    }

    public void xoaPhieu(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from phieu");
        db.close();
    }
}
