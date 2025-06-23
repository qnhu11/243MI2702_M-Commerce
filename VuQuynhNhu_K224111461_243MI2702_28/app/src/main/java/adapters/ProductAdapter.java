package adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.vuquynhnhu.vuquynhnhu_k224111461_243mi2702.R;

import models.Product;

public class ProductAdapter extends ArrayAdapter<Product> {
    ImageView imgProduct;
    TextView txtProductId;
    TextView txtProductName;
    TextView txtProductCode;
    TextView txtProductUnitPrice;
    ImageView imgCart;
    Activity context;
    int resource;

    public ProductAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);

        imgProduct = item.findViewById(R.id.imgProduct);
        txtProductId = item.findViewById(R.id.txtProductId);
        txtProductName = item.findViewById(R.id.txtProductName);
        txtProductCode = item.findViewById(R.id.txtProductCode);
        txtProductUnitPrice = item.findViewById(R.id.txtProductUnitPrice);
        imgCart = item.findViewById(R.id.imgCart);

        Product p = getItem(position);
        if (p != null) {
            Glide.with(context)
                    .load(p.getImageLink())
                    .into(imgProduct);

            txtProductId.setText(String.valueOf(p.getId()));
            txtProductName.setText(p.getProductName());
            txtProductCode.setText(p.getProductCode());
            txtProductUnitPrice.setText(p.getUnitPrice() + " VND");
        }

        return item;
    }
}
