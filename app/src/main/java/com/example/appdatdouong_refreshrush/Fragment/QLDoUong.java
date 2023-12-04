package com.example.appdatdouong_refreshrush.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appdatdouong_refreshrush.Adapter.DoUongAdapter;
import com.example.appdatdouong_refreshrush.Dao.DoUongDAO;
import com.example.appdatdouong_refreshrush.Model.DoUong;
import com.example.appdatdouong_refreshrush.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class QLDoUong extends Fragment {

    ListView lvdoUong;
    ArrayList<DoUong> list;
    static DoUongDAO dao;
    DoUongAdapter adapter;
    DoUong item;
    Dialog dialog;
    EditText edid, edTenDoUong, edGiaDoUong, edTinhTrang, edDiaChi;
    Button btnsave, btncancel;
    FloatingActionButton fltadd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_do_uong, container, false);
        lvdoUong = view.findViewById(R.id.lv_doUong);
        dao = new DoUongDAO(getContext());
        fltadd = view.findViewById(R.id.fltadd_douong);
        fltadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(), 0);
            }
        });
        lvdoUong.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        capNhatLv();
        return view;
    }
    void capNhatLv() {
        list = (ArrayList<DoUong>) dao.getAll();
        adapter = new DoUongAdapter(getActivity(), this, list);
        lvdoUong.setAdapter(adapter);
    }
    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
                capNhatLv();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }
    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_douong);
        edid = dialog.findViewById(R.id.ed_iddouong);
        edTenDoUong = dialog.findViewById(R.id.ed_tendouong);
        edGiaDoUong = dialog.findViewById(R.id.ed_giadouong);
        edTinhTrang = dialog.findViewById(R.id.ed_trangThai);
        edDiaChi = dialog.findViewById(R.id.ed_diachi);
        btncancel = dialog.findViewById(R.id.btnCancel_DU);
        btnsave = dialog.findViewById(R.id.btnSave_DU);

        edid.setEnabled(false);
        if (type != 0) {
            edid.setText(String.valueOf(item.getIdDoUong()));
            edTenDoUong.setText(item.getTenDoUong());
            edGiaDoUong.setText(String.valueOf(item.getGiaDoUong()));
            edTinhTrang.setText(item.getTinhTrang());
            edDiaChi.setText(item.getDiaChiQuan());
        }
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new DoUong();
                item.setTenDoUong(edTenDoUong.getText().toString());
                item.setGiaDoUong(Integer.parseInt(edGiaDoUong.getText().toString()));
                item.setTinhTrang(edTinhTrang.getText().toString());
                item.setDiaChiQuan(edDiaChi.getText().toString());
                if (validate() > 0) {
                    if (type == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setIdDoUong(Integer.parseInt(edid.getText().toString()));
                        if (dao.update(item) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public int validate() {
        int check = 1;
        if (edTenDoUong.getText().length() == 0 || edDiaChi.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;

        }
        return check;

    }
}


