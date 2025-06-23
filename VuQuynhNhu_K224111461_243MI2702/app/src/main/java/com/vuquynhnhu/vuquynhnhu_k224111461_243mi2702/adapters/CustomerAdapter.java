package com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.adapters;
import android.content.Context;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.R;
import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends ArrayAdapter<Customer> {
    private final Context context;
    private final List<Customer> customerList;
    private final List<Customer> selectedCustomers;

    public CustomerAdapter(Context context, List<Customer> customerList, List<Customer> selectedCustomers) {
        super(context, 0, customerList);
        this.context = context;
        this.customerList = customerList;
        this.selectedCustomers = selectedCustomers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.item_customer, parent, false);

        TextView txtName = convertView.findViewById(R.id.txtCustomerName);
        TextView txtPhone = convertView.findViewById(R.id.txtCustomerPhone);

        Customer customer = customerList.get(position);
        txtName.setText(customer.getName());
        txtPhone.setText(customer.getPhone());

        if (selectedCustomers.contains(customer)) {
            convertView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_light));
        } else {
            convertView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.white));
        }

        return convertView;
    }
}
