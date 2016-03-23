package com.nscc.w0281673.vidplayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Isaac on 2015-12-14.
 */
public class DBHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "vidDB.db";
    public static final String VIDEOS_TABLE_NAME = "Videos";
    public static final String VIDEOS_COLUMN_ID = "video_id";
    public static final String VIDEOS_COLUMN_NAME = "name";
    public static final String VIDEOS_COLUMN_DESC = "description";
    public static final String VIDEOS_COLUMN_TRAILER = "trailer";
    public static final String VIDEOS_COLUMN_THUMBNAIL = "thumbnail";
    public static final String VIDEOS_COLUMN_RATING = "rating";
    public DBHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(
                "create table Videos " + "(video_id integer primary key autoincrement, name text not null," +
                        " description text not null, trailer text not null, thumbnail text not null, rating integer default 0)"
        );
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS Videos");
        onCreate(db);
    }
    public void dropTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS Videos");
        db.close();
    }
    public boolean insertVideo(String name, String description, String trailer, String thumbnail, int defRating)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("trailer", trailer);
        contentValues.put("thumbnail", thumbnail);
        contentValues.put("rating", defRating);
        db.insert("Videos", null, contentValues);
        db.close();
        return true;
    }
    public void addRating(int id, int rating)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("rating", rating);
        db.update("Videos", contentValues, "video_id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
    public void alterTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("ALTER TABLE Videos ADD COLUMN rating INTEGER DEFAULT 0");
        db.close();
    }
    public ArrayList<String> getData(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> arrList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from Videos where video_id="+id+"", null);
        while(cursor.isAfterLast()==false)
        {
            cursor = db.rawQuery("select * from Videos where video_id="+id+"", null);
            arrList.add(cursor.getString(cursor.getColumnIndex(VIDEOS_COLUMN_TRAILER)));
            cursor.moveToNext();
        }
        db.close();
        return arrList;
    }

    public int numberOfRows()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, VIDEOS_TABLE_NAME);
        return numRows;
    }
    public boolean updateVideo(Integer id, String name, String description, String trailer, String thumbnail)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("trailer", trailer);
        contentValues.put("thumbnail", thumbnail);
        db.update("Videos", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        db.close();
        return true;
    }
    public void deleteVideo(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Videos", VIDEOS_COLUMN_ID + " = ?", new String[]{Integer.toString(id)});
        db.close();
    }
    public void deleteAllVideos()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Videos", null, null);
        db.close();
    }
    public ArrayList<String> getAllVideos()
    {
        ArrayList<String> arrList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Videos", null);
        cursor.moveToFirst();

        while(cursor.isAfterLast()==false)
        {
            arrList.add(cursor.getString(cursor.getColumnIndex(VIDEOS_COLUMN_NAME)));
            cursor.moveToNext();
        }
        db.close();
        return arrList;
    }
    public ArrayList<String> getAllDescriptions()
    {
        ArrayList<String> arrList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Videos", null);
        cursor.moveToFirst();

        while(cursor.isAfterLast()==false)
        {
            arrList.add(cursor.getString(cursor.getColumnIndex(VIDEOS_COLUMN_DESC)));
            cursor.moveToNext();
        }
        db.close();
        return arrList;
    }
    public ArrayList<String> getAllTrailers()
    {
        ArrayList<String> arrList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Videos", null);
        cursor.moveToFirst();

        while(cursor.isAfterLast()==false)
        {
            arrList.add(cursor.getString(cursor.getColumnIndex(VIDEOS_COLUMN_TRAILER)));
            cursor.moveToNext();
        }
        db.close();
        return arrList;
    }
    public ArrayList<String> getAllThumbnails()
    {
        ArrayList<String> arrList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Videos", null);
        cursor.moveToFirst();

        while(cursor.isAfterLast()==false)
        {
            arrList.add(cursor.getString(cursor.getColumnIndex(VIDEOS_COLUMN_THUMBNAIL)));
            cursor.moveToNext();
        }
        db.close();
        return arrList;
    }
    public ArrayList<Integer> getAllRatings()
    {
        ArrayList<Integer> arrList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Videos", null);
        cursor.moveToFirst();

        while(cursor.isAfterLast()==false)
        {
            Integer x = cursor.getInt(cursor.getColumnIndex(VIDEOS_COLUMN_RATING));
            arrList.add(cursor.getInt(cursor.getColumnIndex(VIDEOS_COLUMN_RATING)));
            cursor.moveToNext();
        }
        db.close();
        return arrList;
    }
}
