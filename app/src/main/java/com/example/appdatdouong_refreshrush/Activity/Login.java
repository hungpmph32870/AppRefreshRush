package com.example.appdatdouong_refreshrush.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.appdatdouong_refreshrush.Dao.NguoiDungDAO;
import com.example.appdatdouong_refreshrush.Fragment.TrangChu;
import com.example.appdatdouong_refreshrush.Menu_navigation;
import com.example.appdatdouong_refreshrush.R;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {
    Button BTN_login_DangNhap, BTN_login_DangKy;
    TextInputLayout TXTL_login_TenDN, TXTL_login_MatKhau;
    NguoiDungDAO ndDAO;
    CheckBox chkluumk;
    String user, pass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TXTL_login_TenDN = findViewById(R.id.txtl_login_TenDN);
        TXTL_login_MatKhau = findViewById(R.id.txtl_login_MatKhau);
        BTN_login_DangNhap = findViewById(R.id.btn_login_DangNhap);
        BTN_login_DangKy = findViewById(R.id.btn_login_dangky);
        chkluumk = findViewById(R.id.chkluumk);
        ndDAO = new NguoiDungDAO(this);

        SharedPreferences pref = getSharedPreferences("USER_NEW", MODE_PRIVATE);
         String user = pref.getString("TENTAIKHOAN", "");
         String pass = pref.getString("MATKHAU", "");
        Boolean rem = pref.getBoolean("REMEMBER", false);
        TXTL_login_TenDN.getEditText().setText(user);
        TXTL_login_MatKhau.getEditText().setText(pass);
        chkluumk.setChecked(rem);

        BTN_login_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
        BTN_login_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checklogin();
            }
        });
    }
    public void rememberUser(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_NEW", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (!status){
            editor.clear();
        }else {
            editor.putString("TENTAIKHOAN", u);
            editor.putString("MATKHAU", p);
            editor.putBoolean("REMEMBER", status);
        }
        editor.commit();
    }
    public void checklogin(){
        user = TXTL_login_TenDN.getEditText().getText().toString();
        pass = TXTL_login_MatKhau.getEditText().getText().toString();
        if (user.trim().isEmpty() || pass.trim().isEmpty()){
            Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
        }else {
            if (ndDAO.checklogin(user, pass) > 0){
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                rememberUser(user, pass, chkluumk.isChecked());
                Intent intent = new Intent(Login.this, Menu_navigation.class);
                intent.putExtra("TENTAIKHOAN", user);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }
}