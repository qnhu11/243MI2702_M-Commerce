package com.vuquynhnhu.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vuquynhnhu.models.Customer;
import com.vuquynhnhu.models.ListCustomer;

import java.util.ArrayList;

public class CustomerConnector {
    ListCustomer listCustomer;
    public CustomerConnector()
    {
        listCustomer=new ListCustomer();
        listCustomer.generate_sample_dataset();
    }
    public ArrayList<Customer> get_all_customers()
    {
        if (listCustomer==null)
        {
            listCustomer=new ListCustomer();
            listCustomer.generate_sample_dataset();
        }
        return listCustomer.getCustomers();
    }
        public ArrayList<Customer> get_customers_by_provider(String provider)
        {
            if (listCustomer==null)
            {
                listCustomer=new ListCustomer();
                listCustomer.generate_sample_dataset();
            }
            ArrayList<Customer>results=new ArrayList<>();
            for (Customer c : listCustomer.getCustomers())
            {
                if (c.getPhone().startsWith(provider))
                {
                  results.add(c);
                }
            }
            return results;
        }
        public boolean isExit(Customer c)
        {
            return listCustomer.isExist(c);
        }
        public void addCustomers(Customer c)
        {
            listCustomer.addCustomers(c);
        }

    /**
     * Đây là hàm truy vấn toàn bộ dữ liệu khách hàng sau đó mô hình hóa hướng đối tượng
     * @param database
     * @return trả về listCustomer
     */
    public ListCustomer getAllCustomers(SQLiteDatabase database)
        {
            listCustomer = new ListCustomer();
            Cursor cursor = database.rawQuery("SELECT * FROM Customer",null);
            while(cursor.moveToNext()){
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String phone = cursor.getString(3);
                String username = cursor.getString(4);
                String password = cursor.getString(5);
                Customer c=new Customer();
                c.setId(id);
                c.setName(name);
                c.setEmail(email);
                c.setPhone(phone);
                c.setUsername(username);
                c.setPassword(password);
                listCustomer.addCustomers(c);
            }
            cursor.close();
            return listCustomer;
        }
}
