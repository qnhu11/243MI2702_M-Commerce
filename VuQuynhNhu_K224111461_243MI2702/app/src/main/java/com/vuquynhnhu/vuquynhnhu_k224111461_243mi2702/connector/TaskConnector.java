package com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.connector;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.models.Task;

import java.util.ArrayList;

public class TaskConnector {
    private SQLiteConnector dbConnector;

    public TaskConnector(Activity activity) {
        dbConnector = new SQLiteConnector(activity);
    }

    // Lấy tất cả task (cho admin)
    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> list = new ArrayList<>();
        SQLiteDatabase database = dbConnector.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM TaskForTeleSales", null);

        while (cursor.moveToNext()) {
            Task task = new Task();
            task.setId(cursor.getInt(0));
            task.setAccountId(cursor.getInt(1));
            task.setTaskTitle(cursor.getString(2));
            task.setDateAssigned(cursor.getString(3));
            task.setIsCompleted(cursor.getInt(4));
            list.add(task);
        }

        cursor.close();
        return list;
    }

    // Lấy task theo account (cho nhân viên)
    public ArrayList<Task> getTasksByAccountId(int accountId) {
        ArrayList<Task> list = new ArrayList<>();
        SQLiteDatabase database = dbConnector.getReadableDatabase();
        Cursor cursor = database.rawQuery(
                "SELECT * FROM TaskForTeleSales WHERE AccountID = ?",
                new String[]{String.valueOf(accountId)}
        );

        while (cursor.moveToNext()) {
            Task task = new Task();
            task.setId(cursor.getInt(0));
            task.setAccountId(cursor.getInt(1));
            task.setTaskTitle(cursor.getString(2));
            task.setDateAssigned(cursor.getString(3));
            task.setIsCompleted(cursor.getInt(4));
            list.add(task);
        }

        cursor.close();
        return list;
    }
    // Thêm Task mới vào bảng TaskForTeleSales
    public int insertTask(int accountId, String taskTitle, String dateAssigned) {
        SQLiteDatabase database = dbConnector.getWritableDatabase();

        String sql = "INSERT INTO TaskForTeleSales (AccountID, TaskTitle, DateAssigned, IsCompleted) " +
                "VALUES (?, ?, ?, 0)";
        database.execSQL(sql, new Object[]{accountId, taskTitle, dateAssigned});

        // Lấy ID vừa insert
        Cursor cursor = database.rawQuery("SELECT last_insert_rowid()", null);
        int taskId = -1;
        if (cursor.moveToFirst()) {
            taskId = cursor.getInt(0);
        }
        cursor.close();

        return taskId;
    }

    // Thêm từng dòng vào bảng TaskForTeleSalesDetails
    public void insertTaskDetail(int taskId, int customerId) {
        SQLiteDatabase database = dbConnector.getWritableDatabase();
        String sql = "INSERT INTO TaskForTeleSalesDetails (TaskForTeleSalesID, CustomerID, IsCalled) VALUES (?, ?, 0)";
        database.execSQL(sql, new Object[]{taskId, customerId});
    }

    public ArrayList<Task> getTasksByAccountAndDate(int accountId, String date) {
        ArrayList<Task> list = new ArrayList<>();
        SQLiteDatabase db = dbConnector.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TaskForTeleSales WHERE AccountID = ? AND DateAssigned = ?",
                new String[]{String.valueOf(accountId), date});

        while (cursor.moveToNext()) {
            Task task = new Task();
            task.setId(cursor.getInt(0));
            task.setAccountId(cursor.getInt(1));
            task.setTaskTitle(cursor.getString(2));
            task.setDateAssigned(cursor.getString(3));
            task.setIsCompleted(cursor.getInt(4));
            list.add(task);
        }

        cursor.close();
        return list;
    }
}
