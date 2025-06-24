package com.vuquynhnhu.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vuquynhnhu.connectors.OrdersViewerConnector;
import com.vuquynhnhu.connectors.SQLiteConnector;
import com.vuquynhnhu.models.OrdersViewer;

import java.util.ArrayList;

import adapters.OrdersViewerAdapter;

public class OrdersViewerActivity extends AppCompatActivity {
    ListView lvOrdersView;
    OrdersViewerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_orders_viewer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }


    private void addViews() {
        lvOrdersView = findViewById((R.id.lvOrdersViewer));
        adapter = new OrdersViewerAdapter(this, R.layout.item_ordersviewer);
        lvOrdersView.setAdapter(adapter);

        SQLiteConnector connector = new SQLiteConnector(this);
        OrdersViewerConnector ovc = new OrdersViewerConnector();
        ArrayList<OrdersViewer> dataset = ovc.getAllOrdersViews(connector.openDatabase());
        adapter.addAll(dataset);

        lvOrdersView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OrdersViewer selectedOrder = (OrdersViewer) parent.getItemAtPosition(position);
                Intent intent = new Intent(OrdersViewerActivity.this, OrderDetailActivity.class);
                intent.putExtra("orderId", selectedOrder.getId());
                intent.putExtra("totalOrderValue", selectedOrder.getTotalOrderValue());
                startActivity(intent);
            }
        });
    }
}