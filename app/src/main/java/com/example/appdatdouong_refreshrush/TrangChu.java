package com.example.appdatdouong_refreshrush;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


public class TrangChu extends Fragment {


    ViewPager viewPager;
    CircleIndicator circleIndicator;
    List<Photo> listPhoto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        viewPager = view.findViewById(R.id.view_page);
        circleIndicator = view.findViewById(R.id.circle_indicator);
        listPhoto = getListPhoto();
        PhotoViewPagerAdapter photoViewPagerAdapter = new PhotoViewPagerAdapter(listPhoto);
        viewPager.setAdapter(photoViewPagerAdapter);
        circleIndicator.setViewPager(viewPager);
        return view;
    }
    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.bannera));
        list.add(new Photo(R.drawable.bannerb));
        list.add(new Photo(R.drawable.bannerc));
        return list;
    }
}