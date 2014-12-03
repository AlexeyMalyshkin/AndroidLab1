package com.androidlab.app.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;

public class NoteProvider extends ContentProvider {

    static final String DB_NAME = "testNote.db";
    static final int DB_VERSION = 1;

    // Таблица
    static final String NOTE_TABLE = "note";

    // Поля
    static final String NOTE_ID = "id";
    static final String NOTE_TITLE = "title";
    static final String NOTE_DESCRIPTION = "description";
    static final String NOTE_DATE = "dateTime";
    static final String NOTE_PRIORITY = "priority";
    static final String NOTE_IMAGE_ID ="imageId";

    // Скрипт создания таблицы
//    static final String DB_CREATE = "create table " + CONTACT_TABLE + "("
//            + CONTACT_ID + " integer primary key autoincrement, "
//            + CONTACT_NAME + " text, " + CONTACT_EMAIL + " text" + ");";

    // // Uri
    // authority
    static final String AUTHORITY = "com.androidlab1.NoteProvider";

    // path
    static final String NOTES_PATH = "notes";

    // Общий Uri
    public static final Uri NOTE_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + NOTES_PATH);

    // Типы данных
    // набор строк
    static final String NOTE_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + NOTES_PATH;

    // одна строка
    static final String NOTE_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + NOTES_PATH;

    //// UriMatcher
    // общий Uri
    static final int URI_NOTES = 1;

    // Uri с указанным ID
    static final int URI_NOTES_ID = 2;

    // описание и создание UriMatcher
    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, NOTES_PATH, URI_NOTES);
        uriMatcher.addURI(AUTHORITY, NOTES_PATH + "/#", URI_NOTES_ID);
    }

    DBHelper dbHelper;
    SQLiteDatabase db;


    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case URI_NOTES:
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = NOTE_TITLE + " ASC";
                }
                break;
            case URI_NOTES_ID: // Uri с ID
                String id = uri.getLastPathSegment();
                // добавляем ID к условию выборки
                if (TextUtils.isEmpty(selection)) {
                    selection = NOTE_ID + " = " + id;
                } else {
                    selection = selection + " AND " + NOTE_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        Cursor cursor =  db.rawQuery( "select rowid _id,* from note", null);
//        Cursor cursor = db.query(NOTE_TABLE, projection, selection,
//                selectionArgs, null, null, sortOrder);

        // просим ContentResolver уведомлять этот курсор
        // об изменениях данных в CONTACT_CONTENT_URI
        cursor.setNotificationUri(getContext().getContentResolver(),
                NOTE_CONTENT_URI);


        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case URI_NOTES:
                return NOTE_CONTENT_TYPE;
            case URI_NOTES_ID:
                return NOTE_CONTENT_ITEM_TYPE;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }


    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
//            db.execSQL(DB_CREATE);
//            ContentValues cv = new ContentValues();
//            for (int i = 1; i <= 3; i++) {
//                cv.put(CONTACT_NAME, "name " + i);
//                cv.put(CONTACT_EMAIL, "email " + i);
//                db.insert(CONTACT_TABLE, null, cv);
//            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
