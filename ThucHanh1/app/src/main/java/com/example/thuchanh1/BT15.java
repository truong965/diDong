package com.example.thuchanh1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuchanh1.Models.KhachHang;
import com.example.thuchanh1.Models.KhachHangAdapter;
import com.example.thuchanh1.databinding.LabelAndInputTextBinding;
import com.example.thuchanh1.databinding.ActivityBt15Binding;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class BT15 extends AppCompatActivity {
    private ActivityBt15Binding binding;
    private KhachHangAdapter khachHangAdapter;
    private LinkedHashMap<KhachHang,Boolean> khachHangs;
    private final int giaSach = 20000;
    private LabelAndInputTextBinding tenKhachHang;
    private LabelAndInputTextBinding soLuongSach;
    private LabelAndInputTextBinding thanhTien;
    private LabelAndInputTextBinding tongKH;
    private LabelAndInputTextBinding tongVIP;
    private LabelAndInputTextBinding tongDoanhThu;
    private CheckBox checkBox;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBt15Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        addActionButton();

    }
    private void initView(){
         tenKhachHang = LabelAndInputTextBinding.bind(binding.tenKhachHang.getRoot());
        soLuongSach = LabelAndInputTextBinding.bind(binding.soLuongSach.getRoot());
         thanhTien = LabelAndInputTextBinding.bind(binding.thanhTien.getRoot());
         tongKH = LabelAndInputTextBinding.bind(binding.tongKH.getRoot());
         tongVIP = LabelAndInputTextBinding.bind(binding.tongVip.getRoot());
         tongDoanhThu = LabelAndInputTextBinding.bind(binding.tongDoanhThu.getRoot());

        tenKhachHang.textView.setText("Tên khách hàng");
        tenKhachHang.editTextText.setHint("nhập tên");

        soLuongSach.textView.setText("Số lượng sách");
        soLuongSach.editTextText.setHint("nhập số lượng sách");
        soLuongSach.editTextText.setInputType(InputType.TYPE_CLASS_NUMBER);

        thanhTien.textView.setText("Thành tiền");
        thanhTien.editTextText.setFocusable(false);
        thanhTien.editTextText.setClickable(false);

        tongKH.textView.setText("Tổng khách hàng");
        tongKH.editTextText.setInputType(InputType.TYPE_CLASS_NUMBER);
        tongKH.editTextText.setFocusable(false);
        tongKH.editTextText.setClickable(false);

        tongVIP.textView.setText("Tổng khách hàng VIP");
        tongVIP.editTextText.setInputType(InputType.TYPE_CLASS_NUMBER);
        tongVIP.editTextText.setFocusable(false);
        tongVIP.editTextText.setClickable(false);

        tongDoanhThu.textView.setText("Tổng doanh thu");
        tongDoanhThu.editTextText.setInputType(InputType.TYPE_CLASS_NUMBER);
        tongVIP.editTextText.setFocusable(false);
        tongVIP.editTextText.setClickable(false);

        khachHangs = new LinkedHashMap<>();
        khachHangs.put(new KhachHang("Nguyen Van A",5,false),false);
        khachHangs.put(new KhachHang("Nguyen Van B",10,true),true);
        khachHangAdapter = new KhachHangAdapter(khachHangs,this);
        RecyclerView rv = findViewById(R.id.recyclerViewKhachHang);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(khachHangAdapter);

        checkBox=  findViewById(R.id.checkBox);
        scrollView =findViewById(R.id.scrollView);
    }
    @SuppressLint("NotifyDataSetChanged")
    private void addActionButton(){
        Button tinhTien = findViewById(R.id.tinhTien);
        Button tiep = findViewById(R.id.tiep);
        Button thongKe = findViewById(R.id.thongKe);
        Button logout = findViewById(R.id.logout);
        tinhTien.setOnClickListener(e->{
            int soLuongSachInt = Integer.parseInt(soLuongSach.editTextText.getText().toString());

            if(checkBox.isChecked()){
                thanhTien.editTextText.setText(String.valueOf(soLuongSachInt*giaSach*0.9));
            }
            else
                thanhTien.editTextText.setText(String.valueOf(soLuongSachInt*giaSach));
        });

        tiep.setOnClickListener(e->{
            KhachHang khachHang = new KhachHang();
            khachHang.setName(tenKhachHang.editTextText.getText().toString());
            khachHang.setSoLuongSach(Integer.parseInt(soLuongSach.editTextText.getText().toString()));
            khachHang.setVip(checkBox.isChecked());
            khachHangAdapter.addKhachHang(khachHang);
            // them vao recyclerview

            // Ẩn bàn phím
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(e.getWindowToken(), 0);
            }

            // Cuộn tới cuối trang
            scrollView.post(() -> scrollView.fullScroll(ScrollView.FOCUS_DOWN));
            clearAll();
        });
        thongKe.setOnClickListener(e->{
            updateThongKe();
        });
        logout.setOnClickListener(e->{
            new AlertDialog.Builder(this)
                    .setTitle("Đăng xuất")
                    .setMessage("Bạn có chắc chắn muốn đăng xuất không?")
                    .setPositiveButton("Có", (dialog, which) -> {finish();})
                    .setNegativeButton("Không", null)
                    .show();
        });
    }
    private void updateThongKe(){
        int tongKHs = 0;
        int tongVIPs = 0;
        int tongDoanhThus = 0;
        for(Map.Entry<KhachHang,Boolean> entry:khachHangs.entrySet()){
            tongKHs++;
            if(entry.getKey().isVip()){
                tongVIPs++;
                tongDoanhThus += entry.getKey().getSoLuongSach()*giaSach*0.9;
            }
            tongDoanhThus += entry.getKey().getSoLuongSach()*giaSach;
        }
        tongKH.editTextText.setText(String.valueOf(tongKHs));
        tongVIP.editTextText.setText(String.valueOf(tongVIPs));
        tongDoanhThu.editTextText.setText(String.valueOf(tongDoanhThus));
    }
    private void clearAll(){
        tenKhachHang.editTextText.setText("");
        soLuongSach.editTextText.setText("");
        thanhTien.editTextText.setText("");
        tenKhachHang.editTextText.setFocusable(true);
    }
}