package com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.k22411cvuquynhnhu;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.R;
import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.adapters.CustomerAdapter;
import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.connector.AccountConnector;
import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.connector.CustomerConnector;
import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.connector.TaskConnector;
import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.models.Account;
import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.models.Customer;

import java.text.SimpleDateFormat;
import java.util.*;

public class CreateTaskActivity extends AppCompatActivity {
    EditText edtTaskTitle;
    Spinner spnEmployee;
    Button btnSelectCustomers, btnCreateTask;
    ListView lvSelectedCustomers;

    ArrayList<Account> employeeList = new ArrayList<>();
    ArrayList<Customer> allCustomers = new ArrayList<>();
    ArrayList<Customer> selectedCustomers = new ArrayList<>();

    CustomerAdapter customerAdapter;
    ArrayAdapter<Account> employeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        // Ánh xạ view
        edtTaskTitle = findViewById(R.id.edtTaskTitle);
        spnEmployee = findViewById(R.id.spnEmployee);
        btnSelectCustomers = findViewById(R.id.btnSelectCustomers);
        btnCreateTask = findViewById(R.id.btnCreateTask);
        lvSelectedCustomers = findViewById(R.id.lvSelectedCustomers);

        // Load dữ liệu
        setupEmployeeSpinner();
        loadCustomers();

        lvSelectedCustomers.setOnItemClickListener((adapterView, view, position, id) -> {
            Customer clicked = allCustomers.get(position);
            if (selectedCustomers.contains(clicked)) {
                selectedCustomers.remove(clicked);
            } else {
                if (selectedCustomers.size() >= 5) {
                    Toast.makeText(this, "You can select up to 5 customers only", Toast.LENGTH_SHORT).show();
                    return;
                }
                selectedCustomers.add(clicked);
            }
            customerAdapter.notifyDataSetChanged(); // cập nhật màu nền
        });

        btnCreateTask.setOnClickListener(v -> {
            String title = edtTaskTitle.getText().toString().trim();
            if (title.isEmpty() || selectedCustomers.isEmpty()) {
                Toast.makeText(this, "Please enter task title and select 5 customers", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lấy nhân viên được chọn
            Account selectedEmp = (Account) spnEmployee.getSelectedItem();
            int employeeId = selectedEmp.getId();

            // Ngày giao task
            String dateAssigned = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

            // Lưu vào SQLite
            TaskConnector connector = new TaskConnector(this);
            int taskId = connector.insertTask(employeeId, title, dateAssigned);

            for (Customer c : selectedCustomers) {
                connector.insertTaskDetail(taskId, c.getId());
            }

            Toast.makeText(this, "Task created successfully", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish(); // quay lại Admin List
        });
    }

    void setupEmployeeSpinner() {
        AccountConnector connector = new AccountConnector(this);
        employeeList = connector.getAllEmployees(); // chỉ nhân viên (type = 0)

        employeeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, employeeList);
        spnEmployee.setAdapter(employeeAdapter);
    }

    void loadCustomers() {
        CustomerConnector connector = new CustomerConnector(this);
        allCustomers = connector.getAllCustomers();

        customerAdapter = new CustomerAdapter(this, allCustomers, selectedCustomers);
        lvSelectedCustomers.setAdapter(customerAdapter);
    }
}
