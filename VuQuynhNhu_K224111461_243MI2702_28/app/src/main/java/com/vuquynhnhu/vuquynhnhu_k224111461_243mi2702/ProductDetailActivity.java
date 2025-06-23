package com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import models.Product;

public class ProductDetailActivity extends AppCompatActivity {
    EditText edt_product_id;
    EditText edt_product_name;
    EditText edt_product_code;
    EditText edt_product_unitprice;
    EditText edt_product_imglink;
    Button btnNew;
    Button btnSave;
    Button btnRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }
    
    private void addEvents() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                process_save_product();
            }
        });
    }

    private void process_save_product() {
        try {
            String idStr = edt_product_id.getText().toString().trim();
            String name = edt_product_name.getText().toString().trim();
            String code = edt_product_code.getText().toString().trim();
            String priceStr = edt_product_unitprice.getText().toString().trim();

            // Kiểm tra dữ liệu hợp lệ
            if (idStr.isEmpty() || name.isEmpty() || code.isEmpty() || priceStr.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            int id = Integer.parseInt(idStr); // Có thể gây lỗi nếu không phải số
            double price = Double.parseDouble(priceStr); // Có thể gây lỗi nếu không phải số

            Product p = new Product();
            p.setId(id);
            p.setProductName(name);
            p.setProductCode(code);
            p.setUnitPrice(price);
            p.setImageLink("https://via.placeholder.com/150"); // mặc định

            Intent intent = getIntent();
            intent.putExtra("NEW_PRODUCT", p);
            setResult(500, intent);
            finish();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "ID và Đơn giá phải là số hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }



    private void addViews() {
        edt_product_id=findViewById(R.id.edt_product_id);
        edt_product_name=findViewById(R.id.edt_product_name);
        edt_product_code=findViewById(R.id.edt_product_code);
        edt_product_unitprice=findViewById(R.id.edt_product_unitprice);
        edt_product_imglink=findViewById(R.id.edt_product_imglink);
        btnNew=findViewById(R.id.btnNew);
        btnSave=findViewById(R.id.btnSave);
        btnRemove=findViewById(R.id.btnRemove);
    }
}