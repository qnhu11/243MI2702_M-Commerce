package com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import adapters.ProductAdapter;
import connectors.ProductConnector;
import models.ListProduct;
import models.Product;

public class ProductManagementActivity extends AppCompatActivity {
    ListView lvAdvancedProduct;
    ProductAdapter adapter;
    ListProduct listProduct;
    ProductConnector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        lvAdvancedProduct=findViewById(R.id.lvAdvancedProduct);
        adapter=new ProductAdapter(ProductManagementActivity.this, R.layout.item_product);
        lvAdvancedProduct.setAdapter(adapter);
        listProduct=new ListProduct();
        connector=new ProductConnector();
        listProduct.generate_sample_product_dataset();
        adapter.addAll(listProduct.getProducts());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.option_menu_product,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menu_add)
        {
            Intent intent=new Intent(ProductManagementActivity.this,ProductDetailActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId()==R.id.menu_about)
        {
            Intent intent=new Intent(ProductManagementActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==300 && resultCode==500)
        {
            // Lấy gói tin ra:
            Product p= (Product) data.getSerializableExtra("NEW_PRODUCT");
            process_save_product(p);
        }
    }

    private void process_save_product(Product p) {
        boolean result=connector.isExit(p);
        if (result==true)
        {

        }
        else
        {
            connector.addProducts(p);
            adapter.clear();
            adapter.addAll(connector.get_all_products());
        }
    }
}