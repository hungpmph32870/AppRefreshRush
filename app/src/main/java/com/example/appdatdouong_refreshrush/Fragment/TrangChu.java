package com.example.appdatdouong_refreshrush.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.appdatdouong_refreshrush.Activity.DanhMuc;
import com.example.appdatdouong_refreshrush.Photo;
import com.example.appdatdouong_refreshrush.PhotoViewPagerAdapter;
import com.example.appdatdouong_refreshrush.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


public class TrangChu extends Fragment {


    ViewPager viewPager;
    CircleIndicator circleIndicator;
    List<Photo> listPhoto;
    ImageButton btndanhmuc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        viewPager = view.findViewById(R.id.view_page);
        btndanhmuc = view.findViewById(R.id.home_btn_danhmuc);
        circleIndicator = view.findViewById(R.id.circle_indicator);
        listPhoto = getListPhoto();
        PhotoViewPagerAdapter photoViewPagerAdapter = new PhotoViewPagerAdapter(listPhoto);
        viewPager.setAdapter(photoViewPagerAdapter);
        circleIndicator.setViewPager(viewPager);

        btndanhmuc.setOnClickListener(this::onClick);
        return view;
    }
    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.bannera));
        list.add(new Photo(R.drawable.bannerb));
        list.add(new Photo(R.drawable.bannerc));
        return list;
    }
    public void onClick(View v){
        int id = v.getId();
        NavigationView navigationView = (NavigationView)getActivity().findViewById(R.id.nav);
        switch (id){
            case R.id.home_btn_danhmuc:
                Intent intent = new Intent(getActivity(), DanhMuc.class);
                startActivity(intent);
                break;
        }
    }
}