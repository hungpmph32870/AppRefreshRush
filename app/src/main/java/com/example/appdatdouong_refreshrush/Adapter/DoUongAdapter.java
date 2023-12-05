package com.example.appdatdouong_refreshrush.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdatdouong_refreshrush.Dao.DoUongDAO;
import com.example.appdatdouong_refreshrush.Model.DoUong;
import com.example.appdatdouong_refreshrush.R;

import java.util.ArrayList;

public class DoUongAdapter extends RecyclerView.Adapter<DoUongAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<DoUong> list;
    DoUongDAO dao;

    TextView txtid, txtten, txtgia, txttinhTrang, txtdiaChi;
    Button btndelete;


    public DoUongAdapter(Context context, ArrayList<DoUong> list) {
        this.context = context;
        this.list = list;
        dao = new DoUongDAO(context);

    }



    @NonNull
    @Override
    public DoUongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_do_uong, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoUongAdapter.ViewHolder holder, int position) {
        DoUong obj = list.get(position);
        holder.txtten.setText("Tên: "+obj.getTenDoUong());
        holder.txtgia.setText(String.valueOf("Giá: "+obj.getGiaDoUong()+" VNĐ"));
        holder.txttinhTrang.setText("Trạng Thái: "+obj.getTinhTrang());
        holder.txtdiaChi.setText("Địa chỉ quán: "+obj.getDiaChiQuan());
        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =new AlertDialog.Builder(context);
                builder.setTitle("Cảnh Báo");
                builder.setMessage("Bạn Có Chắc Chắn Muốn Xóa Không ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (dao.delete(obj.getIdDoUong())){
                            list.clear();
                            list.addAll(dao.selectAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Không xóa", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.show();
                dialog.show();
            }
        });
        holder.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(obj);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtten, txtgia, txttinhTrang, txtdiaChi;
        Button btndelete, btnupdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.tvTenDoUong);
            txtgia = itemView.findViewById(R.id.tvGiaDoUong);
            txttinhTrang = itemView.findViewById(R.id.tvtrangthaiDoUong);
            txtdiaChi = itemView.findViewById(R.id.tvdiachiDoUong);
            btndelete = itemView.findViewById(R.id.btnDeleteDoUong);
            btnupdate = itemView.findViewById(R.id.btnUpdateDoUong);
        }
    }
    public void update(DoUong obj){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_douong, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edten = view.findViewById(R.id.ed_tendouong);
        EditText edgia = view.findViewById(R.id.ed_giadouong);
        EditText edtinhtrang = view.findViewById(R.id.ed_trangThai);
        EditText eddiachi = view.findViewById(R.id.ed_diachi);
        Button btnup = view.findViewById(R.id.btnSave_DU);

        edten.setText(obj.getTenDoUong());
        edgia.setText(String.valueOf(obj.getGiaDoUong()));
        edtinhtrang.setText(obj.getTinhTrang());
        eddiachi.setText(obj.getDiaChiQuan());
        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obj.setTenDoUong(edten.getText().toString());
                obj.setGiaDoUong(Integer.parseInt(edgia.getText().toString()));
                obj.setTinhTrang(edtinhtrang.getText().toString());
                obj.setDiaChiQuan(eddiachi.getText().toString());
                if (dao.update(obj)){
                    list.clear();
                    list.addAll(dao.selectAll());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else {
                    Toast.makeText(context, "Update k thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
