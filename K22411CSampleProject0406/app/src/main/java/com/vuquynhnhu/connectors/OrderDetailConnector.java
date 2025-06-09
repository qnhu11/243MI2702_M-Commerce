package com.vuquynhnhu.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vuquynhnhu.models.OrderDetails;

import java.util.ArrayList;

public class OrderDetailConnector {

    public ArrayList<OrderDetails> getOrderDetailsByOrderId(SQLiteDatabase database, int orderId) {
        ArrayList<OrderDetails> list = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ");
        builder.append("od.Id, ");
        builder.append("od.OrderId, ");
        builder.append("od.ProductId, ");
        builder.append("p.Name AS ProductName, ");
        builder.append("od.Quantity, ");
        builder.append("od.Price, ");
        builder.append("od.Discount, ");
        builder.append("od.VAT, ");
        builder.append("ROUND((od.Quantity * od.Price - (od.Discount / 100.0) * od.Quantity * od.Price) * (1 + od.VAT / 100.0)) AS TotalValue ");
        builder.append("FROM OrderDetails od ");
        builder.append("JOIN Product p ON od.ProductId = p.Id ");
        builder.append("WHERE od.OrderId = ?");

        String sql = builder.toString();

        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(orderId)});
        while (cursor.moveToNext()) {
            OrderDetails od = new OrderDetails();
            od.setId(cursor.getInt(0));
            od.setOrderId(cursor.getInt(1));
            od.setProductId(cursor.getInt(2));
            od.setProductName(cursor.getString(3)); // cần khai báo field productName trong model
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
}
