package adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vuquynhnhu.k22411csampleproject.R;
import com.vuquynhnhu.models.Product;

public class ProductAdapter extends ArrayAdapter <Product> {
    ImageView imgProduct;
    TextView txtProductId;
    TextView txtProductName;
    TextView txtProductQuantity;
    TextView txtProductPrice;
    ImageView imgCart;
    Activity context;
    int resource;
    public ProductAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater=this.context.getLayoutInflater();
        // Nhân hản giao diện theo từ vị trí position mà dữ liệu được duyệt qua:
        View item=inflater.inflate(this.resource,null);
        //luc này: Toàn bộ view nằm trong layout resource(item_advanced_product)
        //sẽ được mô hình hoá hướng đối tượng và được quản lý bởi biến item
        //tức là item là tổng tải view
        //như vậy muốn truy xuất tới các view nó thì phải thông qua item
        imgProduct=item.findViewById(R.id.imgProduct);
        txtProductId=item.findViewById(R.id.txtProductId);
        txtProductName=item.findViewById(R.id.txtProductName);
        txtProductQuantity=item.findViewById(R.id.txtProductQuantity);
        txtProductPrice=item.findViewById(R.id.txtProductPrice);
        imgCart=item.findViewById(R.id.imgCart);

        //Lấy mô hình đối tương đang đưc nhân bản ở vị trí position (đối số 1):
        Product p=getItem(position);
        // Rải dữ liệu của Product lên giao diện trong item
        imgProduct.setImageResource(p.getImage_id());
        txtProductId.setText(p.getId()+"");
        txtProductName.setText(p.getName());
        txtProductQuantity.setText(p.getQuantity()+"");
        txtProductPrice.setText(p.getPrice()+"VND");
        // xử lý bấm vào nút mua ... tính sau
        return item;
    }
}
