package com.vuquynhnhu.k22411c_firstdegree;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    //Khai báo các biến để quản lý các ô nhớ của các view:
    EditText edtCoefficientA;
    EditText edtCoefficientB;
    TextView txtResult;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        addViews();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Tạo mảng ngôn ngữ hiển thị trong Spinner, lấy từ file strings.xml để hỗ trợ đa ngôn ngữ
        String[] languages = {
                getString(R.string.select_language),
                getString(R.string.english),
                getString(R.string.vietnamese),
                getString(R.string.french),
                getString(R.string.spanish)};
        spinner = findViewById(R.id.spinner);
        // Tạo adapter để đưa danh sách ngôn ngữ vào Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        // Xử lý khi người dùng chọn một ngôn ngữ
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLang = parent.getItemAtPosition(position).toString();
                // So sánh chuỗi được chọn với các giá trị trong strings.xml để đổi ngôn ngữ
                if (selectedLang.equals(getString(R.string.english))) {
                    setLocal(MainActivity.this, "en");
                    restartActivity(); // Khởi động lại Activity để áp dụng ngôn ngữ mới
                } else if (selectedLang.equals(getString(R.string.vietnamese))) {
                    setLocal(MainActivity.this, "vi");
                    restartActivity();
                } else if (selectedLang.equals(getString(R.string.french))) {
                    setLocal(MainActivity.this, "fr");
                    restartActivity();
                } else if (selectedLang.equals(getString(R.string.spanish))) {
                    setLocal(MainActivity.this, "es");
                    restartActivity();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Không làm gì nếu không chọn gì
            }
        });
    }
    // Hàm khởi động lại Activity để áp dụng thay đổi ngôn ngữ
    private void restartActivity() {
        finish(); // Đóng activity hiện tại
        startActivity(getIntent()); // Mở lại activity với intent cũ
    }

    // Hàm thiết lập ngôn ngữ hiển thị cho ứng dụng
    public void setLocal(Activity activity, String langCode) {
        Locale locale = new Locale(langCode); // Tạo đối tượng Locale từ mã ngôn ngữ
        locale.setDefault(locale); // Thiết lập mặc định
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale); // Áp dụng locale mới
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    private void addViews() {
        edtCoefficientA=findViewById(R.id.edtCoefficientA);
        edtCoefficientB=findViewById(R.id.edtCoefficientB);
        txtResult=findViewById(R.id.txtResult);
        spinner = findViewById(R.id.spinner);
    }


    public void do_solution(View view) {
        // Lấy hs a trên giao diện
        String hsa=edtCoefficientA.getText().toString();
        double a=Double.parseDouble(hsa);
        // Lấy hs b trên giao diện
        double b=Double.parseDouble(edtCoefficientB.getText().toString());

        if (a==0 && b==0)
        {
            txtResult.setText(getResources().getText(R.string.title_infinity));
        }
        else if (a==0 && b!=0)
        {
            txtResult.setText(getResources().getText(R.string.title_no_solution));
        }
        else
        {
            double x=-b/a;
            txtResult.setText("x="+x);
        }
    }

    public void do_next(View view) {
        edtCoefficientA.setText("");
        edtCoefficientB.setText("");
        txtResult.setText("");
        //Di chuyển trỏ nhập lệ
        edtCoefficientA.requestFocus();
    }

    public void do_exit(View view) {
        finish();
    }

}