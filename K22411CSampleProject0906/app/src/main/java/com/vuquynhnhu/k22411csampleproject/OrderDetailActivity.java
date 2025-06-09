package com.vuquynhnhu.k22411csampleproject;


import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vuquynhnhu.connectors.OrderDetailConnector;
import com.vuquynhnhu.connectors.SQLiteConnector;
import com.vuquynhnhu.models.OrderDetails;

import java.util.ArrayList;

import adapters.OrderDetailAdapter;

public class OrderDetailActivity extends AppCompatActivity {
    ListView lvOrderDetails;
    OrderDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
    }

    private void addViews() {
        lvOrderDetails = findViewById(R.id.lvOrderDetails);
        adapter = new OrderDetailAdapter(this, R.layout.item_order_detail);
        lvOrderDetails.setAdapter(adapter);

        int orderId = getIntent().getIntExtra("orderId", -1);

        if (orderId != -1) {
            SQLiteConnector connector = new SQLiteConnector(this);
            OrderDetailConnector odc = new OrderDetailConnector();
            ArrayList<OrderDetails> dataset = odc.getOrderDetailsByOrderId(connector.openDatabase(), orderId);
            adapter.addAll(dataset);
        }
        TextView txtTotalOrderValue = findViewById(R.id.txtTotalOrderValue);
        double totalOrderValue = getIntent().getDoubleExtra("totalOrderValue", 0);
        txtTotalOrderValue.setText("Total Order Value: " + totalOrderValue);
    }
}
