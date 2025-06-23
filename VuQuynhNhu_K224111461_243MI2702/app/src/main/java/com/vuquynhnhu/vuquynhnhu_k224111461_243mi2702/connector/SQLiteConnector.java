package com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.connector;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class SQLiteConnector {
    private static final String DATABASE_NAME = "Database.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    private Activity context;
    private SQLiteDatabase database;

    public SQLiteConnector(Activity context) {
        this.context = context;
        copyDatabaseIfNeeded(); // sao chép nếu chưa có
    }

    private String getDatabasePath() {
        return context.getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    // Copy file .sqlite từ assets nếu chưa có
    private void copyDatabaseIfNeeded() {
        File dbFile = new File(getDatabasePath());
        if (!dbFile.exists()) {
            try {
                File dbDir = new File(context.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
                if (!dbDir.exists()) {
                    dbDir.mkdir();
                }

                InputStream is = context.getAssets().open(DATABASE_NAME);
                OutputStream os = new FileOutputStream(dbFile);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }

                os.flush();
                os.close();
                is.close();

                Log.d("SQLiteConnector", "Database copied successfully.");
            } catch (Exception e) {
                Log.e("SQLiteConnector", "Error copying database", e);
            }
        }
    }

    public SQLiteDatabase openDatabase() {
        return SQLiteDatabase.openDatabase(getDatabasePath(), null, SQLiteDatabase.OPEN_READWRITE);
    }

    public SQLiteDatabase getReadableDatabase() {
        if (this.database == null || !this.database.isOpen()) {
            this.database = openDatabase();
        }
        return this.database;
    }
    public SQLiteDatabase getWritableDatabase() {
        if (this.database == null || !this.database.isOpen()) {
            this.database = openDatabase(); // đã dùng OPEN_READWRITE
        }
        return this.database;
    }
}
