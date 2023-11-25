package com.example.appdatdouong_refreshrush;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.appdatdouong_refreshrush.Dao.NguoiDungDAO;
import com.example.appdatdouong_refreshrush.Model.NguoiDung;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Register extends AppCompatActivity {
    NguoiDungDAO ndDAO;
    ArrayList<NguoiDung> list;
    Button BTN_signup_next;
    TextInputLayout TXTL_signup_HoVaTen, TXTL_signup_TenDN, TXTL_signup_Email, TXTL_signup_SDT, TXTL_signup_MatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        BTN_signup_next = findViewById(R.id.btn_signup_next);
        TXTL_signup_HoVaTen = findViewById(R.id.txtl_signup_HoVaTen);
        TXTL_signup_TenDN = findViewById(R.id.txtl_signup_TenDN);
        TXTL_signup_Email = findViewById(R.id.txtl_signup_Email);
        TXTL_signup_SDT = findViewById(R.id.txtl_signup_SDT);
        TXTL_signup_MatKhau = findViewById(R.id.txtl_signup_MatKhau);
        ndDAO= new NguoiDungDAO(this);
        BTN_signup_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = TXTL_signup_TenDN.getEditText().getText().toString();
                String pass = TXTL_signup_MatKhau.getEditText().getText().toString();
                NguoiDung nd = new NguoiDung(user, pass);
                if (ndDAO.insert(nd) >0){
                    Toast.makeText(Register.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Register.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}