package com.example.appdatdouong_refreshrush.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appdatdouong_refreshrush.Fragment.TrangChu;
import com.example.appdatdouong_refreshrush.Model.DoUong;
import com.example.appdatdouong_refreshrush.Model.DoUongNew;
import com.example.appdatdouong_refreshrush.R;

import java.util.ArrayList;
import java.util.List;

public class DoUongNewAdapter extends ArrayAdapter<DoUongNew> {
    private List<DoUongNew> list;
    private Context context;
    private TrangChu fragment;
    TextView txtid, txttennew, txtgianew, txtdiachinew;
    Button btndelete;

    public DoUongNewAdapter(@NonNull Context context, TrangChu fragment, ArrayList<DoUongNew> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_do_uong_new, null);
        }
        final DoUongNew item = list.get(position);
        if (item != null) {
            txtid = v.findViewById(R.id.tvMaDoUong_new);
            txtid.setText("ID: "+item.getIdNew());
            txttennew = v.findViewById(R.id.tvTenDoUong_new);
            txttennew.setText("Tên đồ uống: "+item.getTenNew());
            txtgianew = v.findViewById(R.id.tvGiaDoUong_new);
            txtgianew.setText("Giá: "+item.getGiaNew()+" VNĐ");
            txtdiachinew = v.findViewById(R.id.tvdiachiDoUong_new);
            txtdiachinew.setText("Địa chỉ quán: "+item.getDiaChiNew());
            btndelete = v.findViewById(R.id.btnDeleteDoUong_new);
        }

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // gọi phương thức xóa
                fragment.xoa(String.valueOf(item.getIdNew()));

            }
        });
        return v;
    }
}
