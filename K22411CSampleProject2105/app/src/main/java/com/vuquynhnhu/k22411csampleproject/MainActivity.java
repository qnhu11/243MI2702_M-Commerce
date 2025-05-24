package com.vuquynhnhu.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ImageView imgEmployee;
    TextView txtEmloyee;
    ImageView imgCustomer;
    TextView txtCustomer;
    ImageView imgCategory;
    TextView txtCategory;
    ImageView imgProduct;
    TextView txtProduct;
    ImageView imgAdvancedProduct;
    TextView txtAdvancedProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        addViews();
        addEvents();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.edt_customer_email), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void addEvents() {
        imgEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Goi code mo man hinh quan tri nhan su
                openEmployeeManagementActivity();
            }
        });
        txtEmloyee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Goi code mo man hinh quan tri nhan su
                openEmployeeManagementActivity();
            }
        });
        imgCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Goi code mo man hinh quan tri nhan su
                openCustomerManagementActivity();
            }
        });
        txtCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Goi code mo man hinh quan tri nhan su
                openCustomerManagementActivity();
            }
        });
//        imgCategory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Goi code mo man hinh quan tri nhan su
//                openCategoryManagementActivity();
//            }
//        });
//        txtCategory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Goi code mo man hinh quan tri nhan su
//                openCategoryManagementActivity();
//            }
//        });
        imgProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Goi code mo man hinh quan tri nhan su
                openProductManagementActivity();
            }
        });
        txtProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Goi code mo man hinh quan tri nhan su
                openProductManagementActivity();
            }
        });
    }
    void openEmployeeManagementActivity(){
        Intent intent= new Intent(MainActivity.this, EmployeeManagementActivity.class);
        startActivity(intent);
    }

    void openCustomerManagementActivity(){
        Intent intent= new Intent(MainActivity.this, CustomerManagementActivity.class);
        startActivity(intent);
    }
//    void openCategoryManagementActivity(){
//        Intent intent= new Intent(MainActivity.this, CategoryManagementActivity.class);
//        startActivity(intent);
//    }
    private void openProductManagementActivity(){
        Intent intent= new Intent(MainActivity.this, ProductManagementActivity.class);
        startActivity(intent);
    }

    private void addViews() {
        imgEmployee=findViewById((R.id.imgEmployee));
        txtEmloyee=findViewById((R.id.txtEmployee));
        imgCustomer=findViewById((R.id.imgCustomer));
        txtCustomer=findViewById((R.id.txtCustomer));
        imgCategory=findViewById((R.id.imgAdvancedProduct));
        txtCategory=findViewById((R.id.txtAdvancedProduct));
        imgProduct=findViewById((R.id.imgProduct));
        txtProduct=findViewById((R.id.txtProduct));
        imgAdvancedProduct=findViewById((R.id.imgAdvancedProduct));
        txtAdvancedProduct=findViewById((R.id.txtAdvancedProduct));
    }

}