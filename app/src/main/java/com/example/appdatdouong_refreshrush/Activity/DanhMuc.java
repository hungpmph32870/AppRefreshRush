package com.example.appdatdouong_refreshrush.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appdatdouong_refreshrush.Fragment.TrangChu;
import com.example.appdatdouong_refreshrush.R;

public class DanhMuc extends AppCompatActivity {
    ImageButton btnnuocgiaikhat, btncafe, btnbia, btntrasua, btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc);
        btnnuocgiaikhat = findViewById(R.id.btn_danhmuc_nuocgiaikhat);
        btncafe = findViewById(R.id.btn_danhmuc_cafe);
        btnbia = findViewById(R.id.btn_danhmuc_bia);
        btntrasua = findViewById(R.id.btn_danhmuc_trasua);
        btnback = findViewById(R.id.btn_back_danhmuc);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnnuocgiaikhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DanhMuc.this, DanhMucNuocGiaiKhat.class));
            }
        });
        btncafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DanhMuc.this, DanhMucCoffe.class));
            }
        });
        btnbia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DanhMuc.this, DanhMucBia.class));
            }
        });
        btntrasua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DanhMuc.this, DanhMucTraSua.class));
            }
        });
    }
//    public void backFromTrangChu(View view){
//        Intent intent = new Intent(getApplicationContext(), TrangChu.class);
//        Pair [] pairs = new Pair[1];
//        pairs[0] = new Pair<View, String>(findViewById(R.id.layoutDanhMuc), "trangchu");
//        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
//            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DanhMuc.this, pairs);
//            startActivity(intent, options.toBundle());
//        }else {
//            startActivity(intent);
//        }
//    }
}