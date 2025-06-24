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
    ImageView imgPaymentMethod;
    TextView txtPaymentMethod;
    ImageView imgOrder;
    TextView txtOrder;
    ImageView imgTelephony;
    TextView txtTelephony;
    ImageView imgMultiThreading;
    TextView txtMultiThreading;
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
        imgAdvancedProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Goi code mo man hinh quan tri nhan su
                openAdvancedProductManagementActivity();
            }
        });
        txtAdvancedProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Goi code mo man hinh quan tri nhan su
                openAdvancedProductManagementActivity();
            }
        });
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
        imgPaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPaymentMethodActivity();
            }
        });
        txtPaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPaymentMethodActivity();
            }
        });
        imgOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrdersViewerActivity();
            }
        });
        txtOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrdersViewerActivity();
            }
        });
        imgTelephony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTelephonyActivity();
            }
        });
        txtTelephony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTelephonyActivity();
            }
        });
        imgMultiThreading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMultiThreadingActivity();
            }
        });
        txtMultiThreading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMultiThreadingActivity();
            }
        });
    }

    private void openTelephonyActivity() {
        Intent intent= new Intent(MainActivity.this, TelephonyActivity.class);
        startActivity(intent);
    }
    private void openMultiThreadingActivity() {
        Intent intent= new Intent(MainActivity.this, MultiThreadingCategoriesActivity.class);
        startActivity(intent);
    }

    void openOrdersViewerActivity(){
        Intent intent= new Intent(MainActivity.this, OrdersViewerActivity.class);
        startActivity(intent);
    }
    void openPaymentMethodActivity(){
        Intent intent= new Intent(MainActivity.this, PaymentMethodActivity.class);
        startActivity(intent);
    }
    void openEmployeeManagementActivity(){
        Intent intent= new Intent(MainActivity.this, EmployeeManagementActivity.class);
        startActivity(intent);
    }

    void openCustomerManagementActivity(){
        Intent intent= new Intent(MainActivity.this, CustomerManagementActivity.class);
        startActivity(intent);
    }
    void openAdvancedProductManagementActivity(){
        Intent intent= new Intent(MainActivity.this, AdvancedProductManagementActivity.class);
        startActivity(intent);
    }
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
        imgPaymentMethod=findViewById((R.id.imgPaymentMethod));
        txtPaymentMethod=findViewById((R.id.txtPaymentMethod));
        imgOrder=findViewById((R.id.imgOrder));
        txtOrder=findViewById((R.id.txtOrder));
        imgTelephony=findViewById((R.id.imgTelephony));
        txtTelephony=findViewById((R.id.txtTelephony));
        imgMultiThreading=findViewById((R.id.imgMultiThreading));
        txtMultiThreading=findViewById((R.id.txtMultiThreading));
    }

}