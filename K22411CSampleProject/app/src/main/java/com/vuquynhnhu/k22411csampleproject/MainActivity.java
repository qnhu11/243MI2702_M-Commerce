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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        addViews();
        addEvents();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
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
    }
    void openEmployeeManagementActivity(){
        Intent intent= new Intent(MainActivity.this, EmployeeManagementActivity.class);
        startActivity(intent);
    }

    private void addViews() {
        imgEmployee=findViewById((R.id.imgEmployee));
        txtEmloyee=findViewById((R.id.txtEmployee));
    }
}