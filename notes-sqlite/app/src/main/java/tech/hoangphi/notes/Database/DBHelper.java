package tech.hoangphi.notes.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import tech.hoangphi.notes.Database.NotesContract.*;
import tech.hoangphi.notes.Models.Notes;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "notes.db";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE = "CREATE TABLE "
                + NotesEntry.TABLE_NAME + "("
                + NotesEntry.COLUMN_ID + " INTEGER PRIMARY KEY,"
                + NotesEntry.COLUMN_BODY + " TEXT" + ")";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NotesEntry.TABLE_NAME );
        onCreate(db);
    }

    public List<Notes> getAllNotes(){
        String sql = "SELECT * FROM " + NotesEntry.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        List<Notes> listNotes = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do {
                int id =Integer.parseInt(cursor.getString(0));
                String body = cursor.getString(1);
                listNotes.add(new Notes(id,body));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return listNotes;
    }

    public void AddNotes(Notes note){
        ContentValues cv = new ContentValues();
        cv.put(NotesEntry.COLUMN_BODY, note.getBody());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(NotesEntry.TABLE_NAME, null, cv);
    }

    public void UpdateNotes(Notes note){
        ContentValues cv = new ContentValues();
        cv.put(NotesEntry.COLUMN_BODY, note.getBody());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(NotesEntry.TABLE_NAME, cv, NotesEntry.COLUMN_ID + " = ?", new String[]{String.valueOf(note.getId())});
    }

    public void DeleteNotes(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NotesEntry.TABLE_NAME, NotesEntry.COLUMN_ID + " = ?",new String[]{String.valueOf(id)});
    }
}
