package adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vuquynhnhu.k22411csampleproject.R;
import com.vuquynhnhu.models.OrderDetails;

import java.text.DecimalFormat;
import java.util.List;

public class OrderDetailAdapter extends ArrayAdapter<OrderDetails> {

    Activity context;
    int resource;

    public OrderDetailAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);

        TextView txtProductName = item.findViewById(R.id.txtProductName);
        TextView txtQuantity = item.findViewById(R.id.txtQuantity);
        TextView txtPrice = item.findViewById(R.id.txtPrice);
        TextView txtDiscount = item.findViewById(R.id.txtDiscount);
        TextView txtVAT = item.findViewById(R.id.txtVAT);
        TextView txtTotalValue = item.findViewById(R.id.txtTotalValue);

        OrderDetails od = getItem(position);

        txtProductName.setText(od.getProductName());
        txtQuantity.setText("Quantity: " + od.getQuantity());
        txtPrice.setText("Price: " + od.getPrice());
        txtDiscount.setText("Discount: " + od.getDiscount() + "%");
        txtVAT.setText("VAT: " + od.getVAT() + "%");
        txtTotalValue.setText("TotalValue: " + od.getTotalValue());

        return item;
    }
}
