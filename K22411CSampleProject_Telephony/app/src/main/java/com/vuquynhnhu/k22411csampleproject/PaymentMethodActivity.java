package com.vuquynhnhu.k22411csampleproject;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vuquynhnhu.connectors.CustomerConnector;
import com.vuquynhnhu.connectors.SQLiteConnector;
import com.vuquynhnhu.models.ListPaymentMethod;
import com.vuquynhnhu.models.PaymentMethod;

import java.util.ArrayList;

import adapters.PaymentMethodAdapter;

public class PaymentMethodActivity extends AppCompatActivity {
    ListView lvPaymentMethod;
    PaymentMethodAdapter adapter;
    ListPaymentMethod lpm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_method);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        lvPaymentMethod = findViewById(R.id.lvPaymentMethod);

        adapter = new PaymentMethodAdapter(this, R.layout.item_paymentmethod);
        lvPaymentMethod.setAdapter(adapter);

        lpm = ListPaymentMethod.getAllPaymentMethods(new SQLiteConnector(this).openDatabase());

        ArrayList<PaymentMethod> list = lpm.getPaymentMethods();
        adapter.addAll(list);
    }
}