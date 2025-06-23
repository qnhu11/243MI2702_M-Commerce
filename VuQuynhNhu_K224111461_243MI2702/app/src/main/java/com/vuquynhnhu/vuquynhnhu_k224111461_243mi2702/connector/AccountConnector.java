package com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.connector;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.models.Account;

import java.util.ArrayList;

public class AccountConnector {
    private SQLiteConnector dbConnector;

    public AccountConnector(Activity activity) {
        dbConnector = new SQLiteConnector(activity);
    }

    public ArrayList<Account> getAllAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        SQLiteDatabase db = dbConnector.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Account", null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String username = cursor.getString(1);
                String password = cursor.getString(2);
                int type = cursor.getInt(3); // 0 = employee, 1 = admin

                Account account = new Account(id, username, password, type);
                accounts.add(account);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return accounts;
    }

    public Account checkLogin(String username, String password) {
        SQLiteDatabase db = dbConnector.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM Account WHERE username = ? AND password = ?",
                new String[]{username, password}
        );

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            int type = cursor.getInt(3);
            String uname = cursor.getString(1);
            String pwd = cursor.getString(2);

            cursor.close();
            return new Account(id, uname, pwd, type);
        }

        return null;
    }
    public ArrayList<Account> getAllEmployees() {
        ArrayList<Account> list = new ArrayList<>();
        SQLiteDatabase db = dbConnector.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Account WHERE TypeOfAccount = 0", null);
        while (cursor.moveToNext()) {
            Account account = new Account();
            account.setId(cursor.getInt(0));
            account.setUsername(cursor.getString(1));
            account.setPassword(cursor.getString(2));
            account.setType(cursor.getInt(3));
            list.add(account);
        }
        cursor.close();
        return list;
    }

}
