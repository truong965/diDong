package com.example.baitapthuchanh.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.baitapthuchanh.R;

import java.util.ArrayList;

public class SinhVienAdapter extends BaseAdapter {
    private ArrayList<SinhVien> sinhVienList;
    private Context context;
    public SinhVienAdapter(ArrayList<SinhVien> sinhVienList, Context context) {
        this.sinhVienList = sinhVienList;
        this.context = context;
    }
    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return sinhVienList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext()); // Lấy context từ parent
            convertView = inflater.inflate(R.layout.item_sinh_vien, parent, false);
        }

        CheckBox checkBox = convertView.findViewById(R.id.checkbox);
        EditText hoTenEditText = convertView.findViewById(R.id.hoTenEditText);
        EditText tuoiEditText = convertView.findViewById(R.id.tuoiEditText);
        EditText gioiTinhEditText = convertView.findViewById(R.id.gioiTinhEditText);

        SinhVien sinhVien = sinhVienList.get(position);
        hoTenEditText.setText(sinhVien.getTen());
        tuoiEditText.setText(String.valueOf(sinhVien.getTuoi()));
        gioiTinhEditText.setText(sinhVien.isGioiTinh() ? "Nam" : "Nữ");

        checkBox.setChecked(sinhVien.isChecked());
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> sinhVien.setChecked(isChecked));

        return convertView;  // Đảm bảo có return
    }
    public void addSinhVien(SinhVien sv){
        sinhVienList.add(sv);
        notifyDataSetChanged();
    }
    public void removeCheckedSinhVien(){
        for (int i = sinhVienList.size()-1; i >=0; i--) {
            if(sinhVienList.get(i).isChecked()){
                sinhVienList.remove(i);
            }
        }
        notifyDataSetChanged();
    }
}
