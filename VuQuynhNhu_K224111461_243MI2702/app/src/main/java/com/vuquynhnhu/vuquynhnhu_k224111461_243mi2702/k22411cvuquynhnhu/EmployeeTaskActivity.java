package com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.k22411cvuquynhnhu;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.R;
import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.adapters.TaskAdapter;
import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.connector.TaskConnector;
import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.models.Task;

import java.text.SimpleDateFormat;
import java.util.*;

public class EmployeeTaskActivity extends AppCompatActivity {
    ListView lvTasks;
    ArrayList<Task> taskList;
    TaskAdapter adapter;
    int employeeId = 1; // nhận từ LoginActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_task);

        lvTasks = findViewById(R.id.lvTasks);

        String today = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        TaskConnector connector = new TaskConnector(this);
        taskList = connector.getTasksByAccountAndDate(employeeId, today);

        if (taskList.isEmpty()) {
            Toast.makeText(this, "No task assigned today", Toast.LENGTH_SHORT).show();
        }

        adapter = new TaskAdapter(this, taskList);
        lvTasks.setAdapter(adapter);
    }
}
