package com.vuquynhnhu.k22411csampleproject;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vuquynhnhu.models.Customer;

import java.util.Random;

public class HandlerPostMessageActivity extends AppCompatActivity {
    EditText edtNumberOfCustomer;
    Button btnLoadCustomer;
    TextView txtPercent;
    ProgressBar progressBarPercent;
    ListView lvCustomer;
    ArrayAdapter<Customer> adapter;
    int numb = 0, percent, value;
    Handler handler = new Handler();
    Random random = new Random();
    Customer customerInBackqround=null;
    Runnable foregroundThread = new Runnable() {
        @Override
        public void run() {
            txtPercent.setText(percent + " %");
            progressBarPercent.setProgress(percent);

            if(customerInBackqround!=null)
            {
                adapter.add(customerInBackqround);
            }
            if (percent>=100)
            {
                Toast.makeText(HandlerPostMessageActivity.this,"DONE!!",Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_handler_post_message);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();

    }

    private void addEvents() {
        btnLoadCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCustomerInBackground();
            }
        });
    }

    private void loadCustomerInBackground() {
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<=numb; i++){
                    percent =  i*100/numb; //Percent

                    value = random.nextInt(100);

                    customerInBackqround= new Customer();
                    customerInBackqround.setId(i);
                    customerInBackqround.setName("Customer"+i);
                    customerInBackqround.setEmail("customer"+i+"@gmail.com");
                    String []providers={"098","097","091","094","093","090"};
                    String phone=providers[random.nextInt(providers.length)];
                    for(int p=1; p<=7;p++)
                    {
                        phone=phone+random.nextInt(10);
                    }

                    handler.post(foregroundThread);
                    SystemClock.sleep(100);
                }
            }
        });
        backgroundThread.start();
    }

    private void addViews() {
        edtNumberOfCustomer=findViewById(R.id.edtNumberOfCustomer);
        btnLoadCustomer=findViewById(R.id.btnLoadCustomer);
        txtPercent=findViewById(R.id.txtPercent);
        progressBarPercent=findViewById(R.id.progressBarPercent);
        lvCustomer=findViewById(R.id.lvCustomer);
        adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        lvCustomer.setAdapter(adapter);
    }
}