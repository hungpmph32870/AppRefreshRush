package com.example.appdatdouong_refreshrush.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.appdatdouong_refreshrush.Adapter.DoUongAdapter;
import com.example.appdatdouong_refreshrush.Adapter.DoUongNewAdapter;
import com.example.appdatdouong_refreshrush.Dao.DoUongDAO;
import com.example.appdatdouong_refreshrush.Dao.DoUongNewDAO;
import com.example.appdatdouong_refreshrush.Menu_navigation;
import com.example.appdatdouong_refreshrush.Model.DoUong;
import com.example.appdatdouong_refreshrush.Model.DoUongNew;
import com.example.appdatdouong_refreshrush.Photo;
import com.example.appdatdouong_refreshrush.PhotoViewPagerAdapter;
import com.example.appdatdouong_refreshrush.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class TrangChu extends Fragment {

    private ListView lvDoUongNew;
    ArrayList<DoUongNew> list;
    DoUongNewDAO dao;
    DoUongNewAdapter adapter;
    DoUongNew item;
    Dialog dialog;
    EditText edid, edTenNew, edGiaNew, edDiaChiNew;
    Button btnsave, btncancel;
    FloatingActionButton fltadd;

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private List<Photo> listPhoto;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager.getCurrentItem() == listPhoto.size() - 1) {
                viewPager.setCurrentItem(0);
            } else {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        viewPager = view.findViewById(R.id.view_page);
        circleIndicator = view.findViewById(R.id.circle_indicator);
        listPhoto = getListPhoto();
        PhotoViewPagerAdapter photoViewPagerAdapter = new PhotoViewPagerAdapter(listPhoto);
        viewPager.setAdapter(photoViewPagerAdapter);
        circleIndicator.setViewPager(viewPager);
        //list view do uong new
        lvDoUongNew = view.findViewById(R.id.lv_displayhome_DoUongSale);
        dao = new DoUongNewDAO(getContext());
        fltadd = view.findViewById(R.id.fltadd_trangchu);
        fltadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(), 0);
            }
        });
        lvDoUongNew.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });

        handler.postDelayed(runnable, 2000);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 1000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        capNhatLv();
        return view;
    }
    void capNhatLv() {
        list = (ArrayList<DoUongNew>) dao.getAll();
        adapter = new DoUongNewAdapter(getActivity(), this, list);
        lvDoUongNew.setAdapter(adapter);
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
        dialog.setContentView(R.layout.dialog_douongnew);
        edid = dialog.findViewById(R.id.ed_iddouong_new);
        edTenNew = dialog.findViewById(R.id.ed_tendouong_new);
        edGiaNew = dialog.findViewById(R.id.ed_giadouong_new);
        edDiaChiNew = dialog.findViewById(R.id.ed_diachi_new);
        btncancel = dialog.findViewById(R.id.btnCancel_DUnew);
        btnsave = dialog.findViewById(R.id.btnSave_DUnew);

        edid.setEnabled(false);
        if (type != 0) {
            edid.setText(String.valueOf(item.getIdNew()));
            edTenNew.setText(item.getTenNew());
            edGiaNew.setText(String.valueOf(item.getGiaNew()));
            edDiaChiNew.setText(item.getDiaChiNew());
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
                item = new DoUongNew();
                item.setTenNew(edTenNew.getText().toString());
                item.setGiaNew(Integer.parseInt(edGiaNew.getText().toString()));
                item.setDiaChiNew(edDiaChiNew.getText().toString());
                if (validate() > 0) {
                    if (type == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setIdNew(Integer.parseInt(edid.getText().toString()));
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
        if (edTenNew.getText().length() == 0 || edDiaChiNew.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;

        }
        return check;

    }
    private List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.bannera));
        list.add(new Photo(R.drawable.bannerb));
        list.add(new Photo(R.drawable.bannerc));
        return list;
    }

    @Override
    public void onPause() {
        super.onPause();
        viewPager.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewPager.postDelayed(runnable, 1000);
    }
}