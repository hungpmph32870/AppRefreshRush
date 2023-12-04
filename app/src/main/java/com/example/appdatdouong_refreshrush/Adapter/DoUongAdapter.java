package com.example.appdatdouong_refreshrush.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdatdouong_refreshrush.Model.DoUong;
import com.example.appdatdouong_refreshrush.Fragment.QLDoUong;
import com.example.appdatdouong_refreshrush.R;

import java.util.ArrayList;
import java.util.List;

public class DoUongAdapter extends ArrayAdapter<DoUong> {

    private  List<DoUong>list;

    private Context context;
    private QLDoUong fragment;
    TextView txtid, txtten, txtgia, txttinhTrang, txtdiaChi;
    Button btndelete;


    public DoUongAdapter(Context context, QLDoUong fragment, ArrayList<DoUong> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;

    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_do_uong, null);
        }
        final DoUong item = list.get(position);
        if (item != null) {
            txtid = v.findViewById(R.id.tvMaDoUong);
            txtid.setText("ID: "+item.getIdDoUong());
            txtten = v.findViewById(R.id.tvTenDoUong);
            txtten.setText("Tên đồ uống: "+item.getTenDoUong());
            txtgia = v.findViewById(R.id.tvGiaDoUong);
            txtgia.setText("Giá: "+item.getGiaDoUong()+" VNĐ");
            txttinhTrang = v.findViewById(R.id.tvtrangthaiDoUong);
            txttinhTrang.setText("Trạng thái: "+item.getTinhTrang());
            txtdiaChi = v.findViewById(R.id.tvdiachiDoUong);
            txtdiaChi.setText("Địa chỉ quán: "+item.getDiaChiQuan());
            btndelete = v.findViewById(R.id.btnDeleteDoUong);
        }

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // gọi phương thức xóa
                fragment.xoa(String.valueOf(item.getIdDoUong()));

            }
        });
        return v;
    }



}
