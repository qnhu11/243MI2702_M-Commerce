package com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.k22411cvuquynhnhu;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.R;
import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.connector.AccountConnector;
import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.models.Account;

public class LoginActivity extends AppCompatActivity {
    EditText edtUserName;
    EditText edtPassword;
    RadioButton radioAdmin;
    RadioButton radioEmployee;
    Button btnLogin;

    String DATABASE_NAME="Database.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.loginLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
        AccountConnector connector = new AccountConnector(this);
        connector.getAllAccounts(); // ép chạy copyDatabaseIfNeeded() trong SQLiteConnector
    }

    private void addViews() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        radioAdmin = findViewById(R.id.radioAdmin);
        radioEmployee = findViewById(R.id.radioEmployee);
        btnLogin = findViewById(R.id.btnLogin);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.option_menu_about,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if (item.getItemId()==R.id.menu_about)
        {
            Intent intent=new Intent(LoginActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    public void do_login(View view) {
        String username = edtUserName.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        AccountConnector connector = new AccountConnector(this);
        Account account = connector.checkLogin(username, password);

        if (account == null) {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            return;
        }

        int userType = account.getType(); // 0 = employee, 1 = admin

        if (radioAdmin.isChecked() && userType == 1) {
            Intent intent = new Intent(LoginActivity.this, AdminTaskActivity.class);
            startActivity(intent);
        } else if (radioEmployee.isChecked() && userType == 0) {
            Intent intent = new Intent(LoginActivity.this, EmployeeTaskActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Role mismatch. Please select the correct role.", Toast.LENGTH_SHORT).show();
        }
    }
}