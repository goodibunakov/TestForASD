package ru.goodibunakov.testforasd.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ru.goodibunakov.testforasd.model.DbItem;
import ru.goodibunakov.testforasd.model.Item;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "news.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "News";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_LINK = "link";
    public static final String COLUMN_CONTENT = "description";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_IMAGE_LINK = "image_link";
    public static final String COLUMN_CATEGORY = "category";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_LINK + " TEXT, " +
                COLUMN_CONTENT + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_IMAGE_LINK + " TEXT, " +
                COLUMN_CATEGORY + " TEXT);");
    }

    public void saveToDb(List<Item> items) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        for (Item item : items) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, item.getTitle());
            values.put(COLUMN_LINK, item.getLink());
            values.put(COLUMN_CONTENT, item.getDescription());
            values.put(COLUMN_DATE, item.getPubDate());
            values.put(COLUMN_IMAGE_LINK, item.getEnclosure().getUrl());
            values.put(COLUMN_CATEGORY, item.getCategory());
            db.insert(TABLE_NAME, null, values);
        }
        db.close();
    }

    public List<DbItem> getItemsFromDb() {
        String query = "SELECT * FROM " + TABLE_NAME;

        List<DbItem> itemsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        DbItem dbItem;

        if (cursor.moveToFirst()) {
            do {
                dbItem = new DbItem();
                dbItem.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                dbItem.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
                dbItem.setLink(cursor.getString(cursor.getColumnIndex(COLUMN_LINK)));
                dbItem.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT)));
                dbItem.setPubDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
                dbItem.setImageLink(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_LINK)));
                dbItem.setCategory(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)));
                itemsList.add(dbItem);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return itemsList;
    }
}
