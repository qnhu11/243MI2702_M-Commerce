package com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.*;
import android.widget.*;

import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.R;
import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.models.Task;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    private final Context context;
    private final List<Task> taskList;

    public TaskAdapter(Context context, List<Task> taskList) {
        super(context, 0, taskList);
        this.context = context;
        this.taskList = taskList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);

        TextView txtTitle = convertView.findViewById(R.id.txtTaskTitle);
        TextView txtDateAssigned = convertView.findViewById(R.id.txtDateAssigned);

        Task task = taskList.get(position);
        txtTitle.setText(task.getTaskTitle());
        txtDateAssigned.setText("Assigned: " + task.getDateAssigned());

        // Đổi màu nền theo IsCompleted
        if (task.getIsCompleted() == 1) {
            convertView.setBackgroundColor(Color.parseColor("#A5D6A7")); // Màu xanh lá nhạt
        } else {
            convertView.setBackgroundColor(Color.parseColor("#FFFFFF")); // Trắng
        }

        return convertView;
    }
}
