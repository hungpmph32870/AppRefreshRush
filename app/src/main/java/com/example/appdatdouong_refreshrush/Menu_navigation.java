package com.example.appdatdouong_refreshrush;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.appdatdouong_refreshrush.Activity.DanhMuc;
import com.example.appdatdouong_refreshrush.Fragment.DoiMatKhau;
import com.example.appdatdouong_refreshrush.Fragment.GioHang;
import com.example.appdatdouong_refreshrush.Fragment.QuanLyDonHang;
import com.example.appdatdouong_refreshrush.Fragment.ThongTinNguoiDung;
import com.example.appdatdouong_refreshrush.Fragment.ThongTinUngDung;
import com.example.appdatdouong_refreshrush.Fragment.TrangChu;
import com.example.appdatdouong_refreshrush.Fragment.ThongKe;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Menu_navigation extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView nav;
    TextView tvUser;
    View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_navigation);
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav);
        setSupportActionBar(toolbar);
        headerView = nav.getHeaderView(0);
        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);



        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.trangchu){
                    TrangChu tc = new TrangChu();
                    replaceFrg(tc);
                } else if (item.getItemId()==R.id.thongke) {
                    ThongKe tk = new ThongKe();
                    replaceFrg(tk);
                } else if (item.getItemId()==R.id.doimatkhau) {
                    DoiMatKhau dmk = new DoiMatKhau();
                    replaceFrg(dmk);
                } else if (item.getItemId()==R.id.thongtinungdung) {
                    ThongTinUngDung ttud = new ThongTinUngDung();
                    replaceFrg(ttud);
                } else if (item.getItemId()==R.id.profile) {
                    ThongTinNguoiDung ttnd = new ThongTinNguoiDung();
                    replaceFrg(ttnd);
                }else if (item.getItemId()==R.id.giohang){
                    GioHang gh = new GioHang();
                    replaceFrg(gh);
                } else if (item.getItemId()==R.id.quanlydonhang) {
                    QuanLyDonHang qldh = new QuanLyDonHang();
                    replaceFrg(qldh);
                }
                return true;
            }
        });
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
    public void replaceFrg(Fragment frg){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmNav, frg).commit();
    }

}