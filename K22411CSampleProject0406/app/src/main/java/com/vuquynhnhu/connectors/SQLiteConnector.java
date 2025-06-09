package com.vuquynhnhu.connectors;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vuquynhnhu.models.OrderDetails;

import java.util.ArrayList;
import java.util.List;

public class SQLiteConnector {
    String DATABASE_NAME="SalesDatabase.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database=null;
    Activity context;

    public SQLiteConnector() {
    }

    public SQLiteConnector(Activity context) {
        this.context = context;
    }

    public Activity getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }
    public SQLiteDatabase openDatabase()
    {
        database = this.context.openOrCreateDatabase(DATABASE_NAME, this.context.MODE_PRIVATE, null);
        return database;
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }

    public List<OrderDetails> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetails> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT od.Id, od.OrderId, od.ProductId, p.Name AS ProductName, " +
                        "od.Quantity, od.Price, od.Discount, od.VAT, " +
                        "ROUND((od.Quantity * od.Price - (od.Discount / 100.0) * od.Quantity * od.Price) * (1 + od.VAT / 100.0), 2) AS TotalValue " +
                        "FROM OrderDetails od JOIN Product p ON od.ProductId = p.Id " +
                        "WHERE od.OrderId = ?",
                new String[]{String.valueOf(orderId)}
        );

        while (cursor.moveToNext()) {
            OrderDetails od = new OrderDetails();
            od.setId(cursor.getInt(0));
            od.setOrderId(cursor.getInt(1));
            od.setProductId(cursor.getInt(2));
            od.setProductName(cursor.getString(3)); // phải thêm field này vào class OrderDetails
            od.setQuantity(cursor.getInt(4));
            od.setPrice(cursor.getDouble(5));
            od.setDiscount(cursor.getDouble(6));
            od.setVAT(cursor.getDouble(7));
            od.setTotalValue(cursor.getDouble(8));
            list.add(od);
        }

        cursor.close();
        return list;
    }


    private SQLiteDatabase getReadableDatabase() {
        if (this.database == null) {
            return openDatabase(); // mở DB nếu chưa mở
        }
        return this.database;
    }

}
