package com.example.appdatdouong_refreshrush;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.appdatdouong_refreshrush.Dao.NguoiDungDAO;
import com.example.appdatdouong_refreshrush.Model.NguoiDung;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class DoiMatKhau extends Fragment {

    TextInputEditText txtl_DMK_PassOld, txtl_DMK_PassChange, txtl_DMK_RePassChange;
    Button btnSaveUserChange, btnCancleUserChange;
    NguoiDungDAO ndDAO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);
        ndDAO = new NguoiDungDAO(getActivity());
        txtl_DMK_PassOld = v.findViewById(R.id.txtl_DMK_PassOld);
        txtl_DMK_PassChange = v.findViewById(R.id.txtl_DMK_PassChange);
        txtl_DMK_RePassChange = v.findViewById(R.id.txtl_DMK_RePassChange);
        btnCancleUserChange = v.findViewById(R.id.btnCancleUserChange);
        btnSaveUserChange = v.findViewById(R.id.btnSaveUserChange);
        btnCancleUserChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtl_DMK_PassOld.setText("");
                txtl_DMK_PassChange.setText("");
                txtl_DMK_RePassChange.setText("");
            }
        });
        btnSaveUserChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_NEW", Context.MODE_PRIVATE);
                String user = pref.getString("TENTAIKHOAN","");
                if (validate() > 0) {
                    NguoiDung nd = ndDAO.getID(user);
                    nd.setMatKhau(txtl_DMK_PassChange.getText().toString());
                    if (ndDAO.updatePass(nd)>0) {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        txtl_DMK_PassOld.setText("");
                        txtl_DMK_PassChange.setText("");
                        txtl_DMK_RePassChange.setText("");
                    } else {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return v;
    }
    public int validate(){
        int check =1;
        if (txtl_DMK_PassOld.getText().length() == 0 || txtl_DMK_PassChange.getText().length() == 0 || txtl_DMK_RePassChange.getText().length() == 0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            SharedPreferences pref = getActivity().getSharedPreferences("USER_NEW", Context.MODE_PRIVATE);
            String passOld = pref.getString("MATKHAU","");
            String pass = txtl_DMK_PassChange.getText().toString();
            String rePass = txtl_DMK_RePassChange.getText().toString();
            if (!passOld.equals(txtl_DMK_PassOld.getText().toString())){
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!pass.equals(rePass)){
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }

        return check;
    }
}