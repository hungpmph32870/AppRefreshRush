package com.example.appdatdouong_refreshrush.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.appdatdouong_refreshrush.Activity.DanhMuc;
import com.example.appdatdouong_refreshrush.Activity.Deal;
import com.example.appdatdouong_refreshrush.Activity.Sale75;
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
    ImageButton btndanhmuc, btndeal, btnsale75;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager.getCurrentItem()==listPhoto.size()-1){//khi ảnh ở trong viewpager đang là ảnh cuối
                viewPager.setCurrentItem(0);//chuyển ảnh đầu tiên trong list vào viewpager
            }else {//nếu chưa tới ảnh cuối cùng
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);// thực hiện chuyển ảnh tiếp theo
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        viewPager = view.findViewById(R.id.view_page);
        btndanhmuc = view.findViewById(R.id.home_btn_danhmuc);
        btndeal = view.findViewById(R.id.home_btn_deal);
        btnsale75 = view.findViewById(R.id.home_btn_sale75);
        circleIndicator = view.findViewById(R.id.circle_indicator);
        listPhoto = getListPhoto();
        PhotoViewPagerAdapter photoViewPagerAdapter = new PhotoViewPagerAdapter(listPhoto);
        viewPager.setAdapter(photoViewPagerAdapter);
        circleIndicator.setViewPager(viewPager);

        handler.postDelayed(runnable, 2000);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 1000);// sau 1 giây sẽ chuyển ảnh
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btndanhmuc.setOnClickListener(this::onClick);
        btndeal.setOnClickListener(this::onClick);
        btnsale75.setOnClickListener(this::onClick);
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

            case R.id.home_btn_deal:
                Intent intent1 = new Intent(getActivity(), Deal.class);
                startActivity(intent1);
                break;

            case R.id.home_btn_sale75:
                Intent intent2 = new Intent(getActivity(), Sale75.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void onPause() {//khi dừng app
        super.onPause();
        viewPager.removeCallbacks(runnable);//sẽ không tự động chạy ảnh trong viewpager
    }

    @Override
    public void onResume() {// khi sử dụng app
        super.onResume();
        viewPager.postDelayed(runnable, 1000);//sẽ tiếp tục chạy ảnh 1 giây 1 ảnh
    }
}