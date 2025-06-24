package adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vuquynhnhu.k22411csampleproject.R;
import com.vuquynhnhu.k22411csampleproject.SendSmsActivity;
import com.vuquynhnhu.k22411csampleproject.TelephonyActivity;
import com.vuquynhnhu.models.TelephonyInfor;

import java.util.List;

public class TelephonyInforAdapter extends ArrayAdapter<TelephonyInfor> {
    private Activity context;
    private int resource;
    private List<TelephonyInfor> list;

    public TelephonyInforAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }
    public TelephonyInforAdapter(@NonNull Activity context, int resource, List<TelephonyInfor> list) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Nullable
    @Override
    public TelephonyInfor getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);

        TextView txtTelephonyName = item.findViewById(R.id.txtTelephonyName);
        TextView txtTelephonyNumber = item.findViewById(R.id.txtTelephonyNumber);
        ImageView imgDirectCall = item.findViewById(R.id.imgDirectCall);
        ImageView imgDialUp = item.findViewById(R.id.imgDialUp);
        ImageView imgSms = item.findViewById(R.id.imgSendSms);

        TelephonyInfor ti = getItem(position);
        if (ti != null) {
            txtTelephonyName.setText(ti.getName());
            txtTelephonyNumber.setText(ti.getPhone());

            imgDirectCall.setOnClickListener(v -> ((TelephonyActivity) context).directCall(ti));
            imgDialUp.setOnClickListener(v -> ((TelephonyActivity) context).dialUpCall(ti));

            imgSms.setOnClickListener(v -> {
                Intent intent = new Intent(context, SendSmsActivity.class);
                intent.putExtra("TI", ti);
                context.startActivity(intent);
            });
        }

        return item;
    }

    public void setData(List<TelephonyInfor> list) {
        this.list = list;
        clear();       // clear danh sách cũ trong ArrayAdapter
        addAll(list);  // cập nhật lại danh sách mới
        notifyDataSetChanged();
    }
}
