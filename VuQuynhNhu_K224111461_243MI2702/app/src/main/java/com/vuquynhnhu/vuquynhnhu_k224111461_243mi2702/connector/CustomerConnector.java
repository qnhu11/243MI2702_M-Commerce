package com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.connector;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.models.Customer;

import java.util.ArrayList;

public class CustomerConnector {
    private SQLiteConnector dbConnector;

    public CustomerConnector(Activity activity) {
        dbConnector = new SQLiteConnector(activity);
    }

    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> list = new ArrayList<>();
        SQLiteDatabase database = dbConnector.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM Customer", null);

        while (cursor.moveToNext()) {
            Customer c = new Customer();
            c.setId(cursor.getInt(0));
            c.setName(cursor.getString(1));
            c.setPhone(cursor.getString(2));
            list.add(c);
        }

        cursor.close();
        return list;
    }
}