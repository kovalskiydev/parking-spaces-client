package ru.kovalskiy.data.storage.sqlite.impl;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import lombok.AllArgsConstructor;
import ru.kovalskiy.data.models.StreetData;
import ru.kovalskiy.data.storage.sqlite.SQLiteDbHelper;
import ru.kovalskiy.data.storage.sqlite.StreetLocalDataSource;
import ru.kovalskiy.domain.repository.models.Street;

@AllArgsConstructor
public class SQLiteStreetServiceImpl implements StreetLocalDataSource {
    SQLiteDbHelper sqLiteStorage;

    @Override
    public void saveStreet(StreetData streetData) {
        SQLiteDatabase db = sqLiteStorage.getWritableDatabase();
        ContentValues values = new ContentValues();

        db.beginTransaction();

        values.put(SQLiteDbHelper.KEY_NAME, streetData.getName());
        values.put(SQLiteDbHelper.KEY_TOTALSPACE, streetData.getTotalSpace());
        values.put(SQLiteDbHelper.KEY_AVAILABLESPACE, streetData.getAvailableSpace());
        values.put(SQLiteDbHelper.KEY_OCCUPIEDSPACE, streetData.getOccupiedSpace());

        db.insert(SQLiteDbHelper.TABLE_NAME, null, values);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();

        Log.d("SQLiteStreetServiceImpl", streetData.toString());
    }

    @Override
    public void updateStreet(StreetData streetData) {
        SQLiteDatabase db = sqLiteStorage.getReadableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(SQLiteDbHelper.KEY_NAME, streetData.getName());
        contentValues.put(SQLiteDbHelper.KEY_TOTALSPACE, streetData.getTotalSpace());
        contentValues.put(SQLiteDbHelper.KEY_AVAILABLESPACE, streetData.getAvailableSpace());
        contentValues.put(SQLiteDbHelper.KEY_OCCUPIEDSPACE, streetData.getOccupiedSpace());

        db.update(SQLiteDbHelper.TABLE_NAME, contentValues, SQLiteDbHelper.KEY_ID + " = ?", new String[] { String.valueOf(streetData.getId()) });
    }

    @Override
    public Street getStreetById(int id) {
        SQLiteDatabase db = sqLiteStorage.getReadableDatabase();
        Cursor cursor = null;
        Street street = null;
        try {
            cursor = db.query(SQLiteDbHelper.TABLE_NAME, null, SQLiteDbHelper.KEY_ID + " = ?",
                    new String[]{String.valueOf(id)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(SQLiteDbHelper.KEY_NAME));
                @SuppressLint("Range") int totalSpace = cursor.getInt(cursor.getColumnIndex(SQLiteDbHelper.KEY_TOTALSPACE));
                @SuppressLint("Range") int availableSpace = cursor.getInt(cursor.getColumnIndex(SQLiteDbHelper.KEY_AVAILABLESPACE));
                @SuppressLint("Range") int occupiedSpace = cursor.getInt(cursor.getColumnIndex(SQLiteDbHelper.KEY_OCCUPIEDSPACE));
                street = new StreetData(id, name, totalSpace, availableSpace, occupiedSpace).toDomainModel();

                Log.d("SQLiteStreetServiceImpl", "street getted" + street.toString());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
                Log.d("SQLiteStreetServiceImpl", "cursor != null");
            }
            db.close();
            Log.d("SQLiteStreetServiceImpl", "db closed");

        }
        return street;
    }
}
