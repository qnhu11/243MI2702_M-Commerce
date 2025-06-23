package com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.k22411cvuquynhnhu;

import static com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.R.id.main;
import static com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.R.id.main_admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.R;
import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.adapters.TaskAdapter;
import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.connector.TaskConnector;
import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.models.Task;

import java.util.ArrayList;

public class AdminTaskActivity extends AppCompatActivity {
    ListView lvTasks;
    TaskAdapter adapter;
    TaskConnector connector;
    ArrayList<Task> taskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_task);
        ViewCompat.setOnApplyWindowInsetsListener(this.<View>findViewById(main_admin), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lvTasks = findViewById(R.id.lvTasks);
        connector = new TaskConnector(this);
        taskList = connector.getAllTasks(); // hoặc getTasksByAccountId(userId) nếu cần

        adapter = new TaskAdapter(this, taskList);
        lvTasks.setAdapter(adapter);
        Button btnCreateTask = findViewById(R.id.btnCreateTask);
        btnCreateTask.setOnClickListener(v -> {
            Intent intent = new Intent(AdminTaskActivity.this, CreateTaskActivity.class);
            startActivityForResult(intent, 100); // sửa chỗ này
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            taskList = connector.getAllTasks(); // load lại task
            adapter = new TaskAdapter(this, taskList);
            lvTasks.setAdapter(adapter);
        }
    }
}