package com.vuquynhnhu.models;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ListPaymentMethod {
    ListPaymentMethod listPaymentMethod;
    ArrayList<PaymentMethod> paymentMethods;
    public ListPaymentMethod()
    {
        paymentMethods= new ArrayList<>();
    }

    public ArrayList<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(ArrayList<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
    public void gen_payments_method()
    {
        paymentMethods.add(new PaymentMethod(1,"Banking Account","Chuyen khoản NH"));
        paymentMethods.add(new PaymentMethod(2,"MOMO","Thanh toán qua MoMo"));
        paymentMethods.add(new PaymentMethod(3,"CASH","Thanh toán tiền mặt"));
        paymentMethods.add(new PaymentMethod(4,"COD","Nhận hàng rồi thanh toán"));
    }
    public void addPaymentMethod(PaymentMethod p)
    {
        paymentMethods.add(p);
    }
    public static ListPaymentMethod getAllPaymentMethods(SQLiteDatabase database)
    {
        ListPaymentMethod result = new ListPaymentMethod(); // ✅ tạo object mới
        Cursor cursor = database.rawQuery("SELECT * FROM PaymentMethod", null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);

            PaymentMethod pm = new PaymentMethod();
            pm.setId(id);
            pm.setName(name);
            pm.setDescription(description);

            result.addPaymentMethod(pm); // ✅ gọi thông qua object
        }

        cursor.close();
        return result;
    }
}
