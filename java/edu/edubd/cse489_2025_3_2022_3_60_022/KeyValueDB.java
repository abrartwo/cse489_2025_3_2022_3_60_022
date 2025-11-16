package edu.edubd.cse489_2025_3_2022_3_60_022;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class KeyValueDB extends SQLiteOpenHelper {

    static final String DB_NAME = "KEY_VALUE.DB";
    public final String TABLE_KEY_VALUE = "key_value_pairs";
    public final String KEY = "keyname";
    public final String VALUE = "itemvalue";

    public KeyValueDB(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("DB@OnCreate");
        createKeyValueTable(db);
    }

    private void createKeyValueTable(SQLiteDatabase db) {
        try {
            db.execSQL("create table " + TABLE_KEY_VALUE + " (" + KEY
                    + " TEXT PRIMARY KEY, " + VALUE + " TEXT)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //createKeyValueTable(db);
        // SQL statements to change the database schema
    }

    private void handleError(SQLiteDatabase db, Exception e) {
        String errorMsg = e.getMessage().toString();
        if (errorMsg.contains("no such table")) {
            if (errorMsg.contains(TABLE_KEY_VALUE)) {
                createKeyValueTable(db);
            }
        }
    }

    public Cursor execute(String query, String[] args) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = null;
        try {
            res = db.rawQuery(query, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public Boolean insertKeyValue(String key, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY, key);
        cv.put(VALUE, value);
        try {
            long result = db.insert(TABLE_KEY_VALUE, null, cv);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

//	public Cursor getAllKeyValues() {
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor res = db.rawQuery("select * from " + TABLE_KEY_VALUE, null);
//		return res;
//	}

    public boolean updateValueByKey(String key, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY, key);
        cv.put(VALUE, value);
        try {
            int nr = db.update(TABLE_KEY_VALUE, cv, KEY + "=?",
                    new String[]{key});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Integer deleteDataByKey(String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        int isDeleted = 0;
        try {
            isDeleted = db.delete(TABLE_KEY_VALUE, KEY + " = ?", new String[]{key});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    public String getValueByKey(String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res;
        try {
            res = db.rawQuery("SELECT * FROM " + TABLE_KEY_VALUE + " WHERE "
                    + KEY + "='" + key + "'", null);
        } catch (Exception e) {
            handleError(db, e);
            res = db.rawQuery("SELECT * FROM " + TABLE_KEY_VALUE + " WHERE "
                    + KEY + "='" + key + "'", null);
        }
        if (res.getCount() > 0) {
            res.moveToNext();
            return res.getString(1);
        }
        return null;
    }

    //
    public void deleteQuery(String query) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res;
        try {
            db.execSQL(query);
        } catch (Exception e) {
            // e.printStackTrace();
            handleError(db, e);
            db.execSQL(query);
        }
    }
}