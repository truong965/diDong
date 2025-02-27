package com.example.thuchanh1.Models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuchanh1.R;
import com.example.thuchanh1.databinding.LabelAndInputTextBinding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHolder> {
    private LinkedHashMap<KhachHang, Boolean> khachHangs;
    private List<KhachHang> khachHangList;
    private Context context;

    public KhachHangAdapter(LinkedHashMap<KhachHang, Boolean> khachHangs, Context context) {
        this.khachHangs = khachHangs;
        this.khachHangList = new ArrayList<>(khachHangs.keySet()); // Chuyển Map thành List để quản lý
        this.context = context;
    }

    // Tạo ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        LabelAndInputTextBinding tenKhachHang, soLuongSach;
        TextView isVip;

        public ViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            tenKhachHang = LabelAndInputTextBinding.bind(itemView.findViewById(R.id.tenKhachHangItem));
            soLuongSach = LabelAndInputTextBinding.bind(itemView.findViewById(R.id.soLuongSachItem));
            isVip = itemView.findViewById(R.id.isVip);

            tenKhachHang.textView.setText("Tên khách hàng");
            tenKhachHang.editTextText.setFocusable(false);
            tenKhachHang.editTextText.setClickable(false);
            soLuongSach.textView.setText("Số lượng sách");
            soLuongSach.editTextText.setInputType(InputType.TYPE_CLASS_NUMBER);
            soLuongSach.editTextText.setFocusable(false);
            soLuongSach.editTextText.setClickable(false);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khach_hang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KhachHang khachHang = khachHangList.get(position);
        holder.tenKhachHang.editTextText.setText(khachHang.getName());
        holder.soLuongSach.editTextText.setText(String.valueOf(khachHang.getSoLuongSach()));
        holder.isVip.setText(khachHang.isVip() ? "VIP" : "Thường");

        // Xử lý trạng thái checkbox
        holder.checkBox.setOnCheckedChangeListener(null);// Tránh set listener nhiều lần khi RecyclerView tái sử dụng ViewHolder
        holder.checkBox.setChecked(Boolean.TRUE.equals(khachHangs.get(khachHang)));
        // Thiết lập sự kiện khi check/uncheck
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            khachHangs.put(khachHang, isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return khachHangList.size();
    }

    public void addKhachHang(KhachHang khachHang) {
        if (!khachHangs.containsKey(khachHang)) { // Tránh thêm trùng lặp
            khachHangs.put(khachHang, Boolean.FALSE);
            khachHangList.add(khachHang);
            notifyItemInserted(khachHangList.size() - 1); // Chỉ cập nhật item mới
        }
    }

    // Xóa khách hàng đã chọn
    public void removeCheckedKhachHang() {
        Iterator<Map.Entry<KhachHang, Boolean>> iterator = khachHangs.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<KhachHang, Boolean> entry = iterator.next();
            if (Boolean.TRUE.equals(entry.getValue())) {
                int position = khachHangList.indexOf(entry.getKey());
                if (position != -1) {
                    khachHangList.remove(position);
                    notifyItemRemoved(position); // Chỉ cập nhật item bị xóa
                }
                iterator.remove(); // Xóa phần tử an toàn khi đang duyệt danh sách
            }
        }
    }

}
