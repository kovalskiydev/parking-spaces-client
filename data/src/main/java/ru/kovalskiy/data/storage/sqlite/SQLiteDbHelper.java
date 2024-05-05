package ru.kovalskiy.data.storage.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PUBLIC)
public class SQLiteDbHelper extends SQLiteOpenHelper {

    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "streetDb";
    public static String TABLE_NAME = "street";

    public static String KEY_ID = "_id";
    public static String KEY_NAME = "name";
    public static String KEY_TOTALSPACE = "totalSpace";
    public static String KEY_AVAILABLESPACE = "availableSpace";
    public static String KEY_OCCUPIEDSPACE = "occupiedSpace";

    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
    KEY_NAME + " TEXT," +
    KEY_TOTALSPACE + " INTEGER," +
    KEY_AVAILABLESPACE + " INTEGER," +
    KEY_OCCUPIEDSPACE + " INTEGER" + ")";


    public SQLiteDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
