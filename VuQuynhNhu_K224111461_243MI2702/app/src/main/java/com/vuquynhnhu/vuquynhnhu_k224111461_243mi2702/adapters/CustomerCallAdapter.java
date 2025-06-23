//package com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.adapters;
//
//import android.content.Context;
//import android.view.*;
//import android.widget.*;
//import androidx.core.content.ContextCompat;
//import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.R;
//import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.connector.TaskConnector;
//import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.models.TaskDetails;
//import java.util.ArrayList;
//
//public class CustomerCallAdapter extends BaseAdapter {
//    Context context;
//    ArrayList<TaskDetails> list;
//    TaskConnector connector;
//    int taskId;
//
//    public CustomerCallAdapter(Context context, ArrayList<TaskDetails> list, TaskConnector connector, int taskId) {
//        this.context = context;
//        this.list = list;
//        this.connector = connector;
//        this.taskId = taskId;
//    }
//
//    @Override public int getCount() { return list.size(); }
//
//    @Override public Object getItem(int i) { return list.get(i); }
//
//    @Override public long getItemId(int i) { return i; }
//
//    @Override
//    public View getView(int i, View convertView, ViewGroup parent) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
//        TextView txtName = view.findViewById(R.id.txtTaskTitle);
//        TextView txtPhone = view.findViewById(R.id.txtDateAssigned);
//        LinearLayout layout = view.findViewById(R.id.taskLayout);
//
//        TaskDetail c = list.get(i);
//        txtName.setText(c.getCustomerName());
//        txtPhone.setText(c.getCustomerPhone());
//
//        if (c.getIsCalled() == 0) {
//            layout.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow));
//        } else {
//            layout.setBackgroundColor(ContextCompat.getColor(context, android.R.color.white));
//        }
//
//        view.setOnClickListener(v -> {
//            if (c.getIsCalled() == 0) {
//                c.setIsCalled(1);
//                connector.updateIsCalled(c.getId());
//                notifyDataSetChanged();
//
//                if (allCalled()) {
//                    connector.updateTaskCompleted(taskId);
//                    Toast.makeText(context, "All calls completed. Task marked as done.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        return view;
//    }
//
//    private boolean allCalled() {
//        for (TaskDetails t : list) {
//            if (t.getIsCalled() == 0) return false;
//        }
//        return true;
//    }
//}
