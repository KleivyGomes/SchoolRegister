package com.example.gtescolar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.system.ErrnoException;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "APP.db";
    private static final int DATABASE_Version = 1;

    private static final String TABLE_NAME = "Mydatabase";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_DISCIPLINE = "discipline";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_DATA = "date";
    private static final String COLUMN_TYPE_EVALUATE = "evaluate_type";
    private static final String COLUMN_DONE = "done";
    private static final String COLUMN_SEMESTER = "semester";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_DISCIPLINE + " TEXT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_YEAR + " TEXT, " +
                COLUMN_DATA + " TEXT, " +
                COLUMN_TYPE_EVALUATE + " TEXT, " +
                COLUMN_DONE + " TEXT, " +
                COLUMN_SEMESTER + " TEXT);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    boolean addTarefa(String type, String discipline,String title, String year, String data, String evaluate_type, String semester){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TYPE, type);
        cv.put(COLUMN_DISCIPLINE, discipline);
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_YEAR,year);
        cv.put(COLUMN_DATA, data);
        cv.put(COLUMN_TYPE_EVALUATE, evaluate_type);
        cv.put(COLUMN_DONE, String.valueOf(false).trim());
        cv.put(COLUMN_SEMESTER,semester);

        long result = db.insert(TABLE_NAME,null,cv);

        if(result == -1){
           return false;
        }

        return true;
    }

    Cursor ReadAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    boolean deleteData(Integer row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(row_id)});
        if(result == -1){
            return false;
        }
        return true;
    }
    void updateRow(Integer id, boolean done){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DONE,String.valueOf(done));
        long result = db.update(TABLE_NAME, cv, "_id = ?", new String[]{String.valueOf(id)});
    }
}
