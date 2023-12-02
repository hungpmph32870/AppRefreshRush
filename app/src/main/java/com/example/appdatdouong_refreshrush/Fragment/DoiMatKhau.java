package com.example.appdatdouong_refreshrush.Fragment;

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
import com.example.appdatdouong_refreshrush.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class DoiMatKhau extends Fragment {

    private TextInputLayout txtl_DMK_PassOld, txtl_DMK_PassChange, txtl_DMK_RePassChange;
    Button btnSaveUserChange, btnCancleUserChange;
    NguoiDungDAO ndDAO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);
        txtl_DMK_PassOld = v.findViewById(R.id.txtl_DMK_PassOld);
        txtl_DMK_PassChange = v.findViewById(R.id.txtl_DMK_PassChange);
        txtl_DMK_RePassChange = v.findViewById(R.id.txtl_DMK_RePassChange);
        btnCancleUserChange = v.findViewById(R.id.btnCancleUserChange);
        btnSaveUserChange = v.findViewById(R.id.btnSaveUserChange);

        ndDAO = new NguoiDungDAO(getActivity());
        btnCancleUserChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        btnSaveUserChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_NEW", Context.MODE_PRIVATE);
                String user = pref.getString("TENTAIKHOAN","");
                if (validate() > 0) {
                    NguoiDung nd = ndDAO.getID(user);
                    nd.setMatKhau(txtl_DMK_PassChange.getEditText().getText().toString());
                    if (ndDAO.updatePass(nd) > 0) {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        txtl_DMK_PassOld.getEditText().setText("");
                        txtl_DMK_PassChange.getEditText().setText("");
                        txtl_DMK_RePassChange.getEditText().setText("");
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
        if (txtl_DMK_PassOld.getEditText().getText().length() == 0 || txtl_DMK_PassChange.getEditText().getText().length() == 0 || txtl_DMK_RePassChange.getEditText().getText().length() == 0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            SharedPreferences pref = getActivity().getSharedPreferences("USER_NEW", Context.MODE_PRIVATE);
            String passOld = pref.getString("MATKHAU","");
            String pass = txtl_DMK_PassChange.getEditText().getText().toString();
            String rePass = txtl_DMK_RePassChange.getEditText().getText().toString();
            if (!passOld.equals(txtl_DMK_PassOld.getEditText().getText().toString())){
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
//    private void changePassword() {
//        String oldPassword = txtl_DMK_PassOld.getEditText().getText().toString().trim();
//        String newPassword = txtl_DMK_PassChange.getEditText().getText().toString().trim();
//        String reEnteredPassword = txtl_DMK_RePassChange.getEditText().getText().toString().trim();
//
//        SharedPreferences preferences = requireContext().getSharedPreferences("USER_NEW", requireContext().MODE_PRIVATE);
//        String id = preferences.getString("IDNGUOIDUNG", "");
//
//        // Validate input
//        if (oldPassword.isEmpty() || newPassword.isEmpty() || reEnteredPassword.isEmpty()) {
//            Toast.makeText(requireContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (!newPassword.equals(reEnteredPassword)) {
//            Toast.makeText(requireContext(), "mật khẩu mới không khớp", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        String currentUserId = id;
//        int result = ndDAO.updatePass(currentUserId, oldPassword, newPassword);
//
//        if (result > 0) {
//            Toast.makeText(requireContext(), "Đã thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
//        } else if (result == -1) {
//            Toast.makeText(requireContext(), "Mật mã cũ không chính xác", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(requireContext(), "Không thể thay đổi mật khẩu", Toast.LENGTH_SHORT).show();
//        }
}