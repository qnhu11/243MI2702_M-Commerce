package com.vuquynhnhu.k22411csampleproject;

import android.app.ComponentCaller;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vuquynhnhu.connectors.CustomerConnector;
import com.vuquynhnhu.connectors.SQLiteConnector;
import com.vuquynhnhu.models.Customer;
import com.vuquynhnhu.models.ListCustomer;

public class CustomerManagementActivity extends AppCompatActivity {
    ListView lvCustomer;
    ArrayAdapter<Customer> adapter;
    CustomerConnector connector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.edt_customer_email), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }

    private void addEvents()
    {
        lvCustomer.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Customer selected= adapter.getItem(i);
//                adapter.remove(selected);
                Customer c=adapter.getItem(i);
                displayCustomerDetailActivity(c);
                return false;
            }
        });
//        lvCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
//                Customer c=adapter.getItem(i);
//                displayCustomerDetailActivity(c);
//            }
//        });
    }

    private void displayCustomerDetailActivity(Customer c) {
        Intent intent= new Intent(CustomerManagementActivity.this,CustomerDetailActivity.class);
        intent.putExtra("SELECTED_CUSTOMER",c);
        startActivity(intent);
    }

    private void addViews() {
        lvCustomer=findViewById(R.id.lvCustomer);
        adapter= new ArrayAdapter<>(CustomerManagementActivity.this, android.R.layout.simple_list_item_1);
        connector=new CustomerConnector();
        ListCustomer lc= connector.getAllCustomers(new SQLiteConnector(this).openDatabase());
        adapter.addAll(lc.getCustomers());
        lvCustomer.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.option_menu_customer,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menu_new_customer)
        {
            Toast.makeText(CustomerManagementActivity.this,"Mở màn hình thêm mới KH",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(CustomerManagementActivity.this,CustomerDetailActivity.class);
            // đóng gói và đặt mã request code là 300
            startActivityForResult(intent,300);
            //startActivity(intent);
        }
        else if (item.getItemId()==R.id.menu_broadcast_advertising)
        {
            Toast.makeText(CustomerManagementActivity.this,"Gửi quảng cáo hàng loạt tới khách hàng",Toast.LENGTH_LONG).show();
            // Tìm hiểu Firebase Cloud message + push message
        }
        else if (item.getItemId()==R.id.menu_help)
        {
            Toast.makeText(CustomerManagementActivity.this,"Mở website HDSD",Toast.LENGTH_LONG).show();
        }
            return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //trường hợp xử lý cho NEW CUSTOMER ta chỉ quan tâm 300 và 500 do ta định nghĩa:
        if (requestCode==300 && resultCode==500)
        {
            // Lấy gói tin ra:
            Customer c= (Customer) data.getSerializableExtra("NEW_CUSTOMER");
            process_save_customer(c);
        }
    }

    private void process_save_customer(Customer c) {
        boolean result=connector.isExit(c);
        if (result==true)
        {
            // tức là customer này đã tồn tại trong hệ thống
            // họ có như cầu sửa các thông tin kahsch vd : địa chỉ, pp thanh toán,...
            // Sinh viên tự xử lý TH sửa thông ti9688888n
        }
        else
        {
            // Là thêm mới customer vì chuea tồn tại:
            connector.addCustomers(c);
            adapter.clear();
            adapter.addAll(connector.get_all_customers());
        }
    }


}